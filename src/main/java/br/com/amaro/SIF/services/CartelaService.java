package br.com.amaro.SIF.services;

import br.com.amaro.SIF.config.exceptions.CartelaException;
import br.com.amaro.SIF.config.exceptions.InvalidInfoException;
import br.com.amaro.SIF.dto.CartelaDTO;
import br.com.amaro.SIF.dto.NovaCartelaDTO;
import br.com.amaro.SIF.dto.NovoSeloDTO;
import br.com.amaro.SIF.dto.OwnerCartelasDTO;
import br.com.amaro.SIF.form.ConviteForm;
import br.com.amaro.SIF.form.NovaCartelaForm;
import br.com.amaro.SIF.form.NovoSeloForm;
import br.com.amaro.SIF.repository.models.Cartela;
import br.com.amaro.SIF.repository.models.ModeloCartela;
import br.com.amaro.SIF.repository.models.Usuario;
import br.com.amaro.SIF.repository.CartelaRepository;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartelaService {

    @Value("${sif.config.cartela.serie}")
    private String cartelaSerie;
    @Value("${sif.config.mensagens.cartelacheia}")
    private String carteiraCheiaMessage;
    @Value("${sif.config.mensagens.qtdmaiorquepermitida}")
    private String qtdMaiorQuePermitida;
    @Value("${sif.config.mensagens.cartelaRepetida}")
    private String cartelaRepetida;
    @Value("${sif.config.mensagens.cartelaRepetidaConvite}")
    private String cartelaRepetidaConvite;
    @Autowired
    private CartelaRepository cartelaRepository;
    @Autowired
    private ModeloCartelaRepository modeloCartelaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @Transactional
    public NovaCartelaDTO novaCartela(NovaCartelaForm form) {
        Optional<ModeloCartela> modeloCartelaOpt = modeloCartelaRepository.findById(form.getIdModelo());
        if(modeloCartelaOpt.isEmpty()) {
            throw new CartelaException("Modelo de cartela não existe!");
        }
        ModeloCartela modeloCartela = modeloCartelaOpt.get();
        Usuario usuario = usuarioRepository.findUsuarioByUserName(form.getUsername()).get();
        if(verificaSeJaTemCartela(usuario, modeloCartela)) {
            throw new CartelaException(cartelaRepetida);
        }
        return getNovaCartelaDTO(usuario, modeloCartela);
    }

    @Transactional
    public NovaCartelaDTO adicionaPorConvite(String authorization, ConviteForm form) {
        Long idUsuarioByToken = tokenService.convertTokenAndGetId(authorization);
        Usuario owner = usuarioRepository.getById(idUsuarioByToken);

        ModeloCartela modeloCartela = modeloCartelaRepository.getByCodigoConvite(form.getConvite());
        if(verificaSeJaTemCartela(owner, modeloCartela)) {
            throw new CartelaException(cartelaRepetidaConvite);
        }
        return getNovaCartelaDTO(owner, modeloCartela);
    }

    public NovoSeloDTO novoSelo(String authorization, NovoSeloForm form) {
        Cartela cartela = cartelaRepository.findCartelaBySerie(form.getSerie());
        Long idCriadorByToken = tokenService.convertTokenAndGetId(authorization);
        if(idCriadorByToken != cartela.getModeloCartela().getCriador().getId()) {
            throw new InvalidInfoException("Você não pode adicionar selos em uma cartela de outro criador.");
        }
        if(cartela.isCompleta()) {
            throw new CartelaException(carteiraCheiaMessage);
        }
        if(cartela.getSelos() + form.getQtdSelos() > cartela.getModeloCartela().getQuantidadeSelos()) {
            throw new CartelaException(montaMensagemQtdMaior(cartela.getModeloCartela().getQuantidadeSelos()));
        }
        cartela.setSelos(cartela.getSelos() + form.getQtdSelos());
        if(cartela.getSelos() == cartela.getModeloCartela().getQuantidadeSelos()) {
            cartela.setCompleta(true);
        }
        cartela = cartelaRepository.save(cartela);
        return new NovoSeloDTO(cartela);
    }

    public OwnerCartelasDTO buscaCartelasUsuario(String authorization) {
        Long userIdByToken = tokenService.convertTokenAndGetId(authorization);
        Usuario owner = usuarioRepository.getById(userIdByToken);
        List<Cartela> cartelas = cartelaRepository.findCartelasByOwner(owner);
        List<CartelaDTO> cartelasDTO = new ArrayList<>();
        cartelas.forEach(cartela -> {
            cartelasDTO.add(new CartelaDTO(cartela));
        });
        return new OwnerCartelasDTO(owner.getUsername(), cartelasDTO);
    }

    private NovaCartelaDTO getNovaCartelaDTO(Usuario owner, ModeloCartela modeloCartela) {
        Cartela cartela = new Cartela();
        cartela.setModeloCartela(modeloCartela);
        cartela.setOwner(owner);
        cartela.setDataExpiracao(modeloCartela.getDataExpiracao());
        cartela.setSerie(gerarSerie());
        cartela = cartelaRepository.save(cartela);
        return new NovaCartelaDTO(cartela);
    }

    private Boolean verificaSeJaTemCartela(Usuario usuario, ModeloCartela modeloCartela) {
        return cartelaRepository.existsCartelaByOwnerAndModeloCartelaAndCompletaFalse(usuario,modeloCartela);
    }

    private String gerarSerie() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        Long epoch = new Date().getTime();
        return String.format("%s%05d-%d", cartelaSerie ,number, epoch);
    }

    private String montaMensagemQtdMaior(Integer qtd) {
        return qtdMaiorQuePermitida.replace("#selos", qtd.toString());
    }


}
