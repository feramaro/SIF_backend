package br.com.amaro.SIF.services;

import br.com.amaro.SIF.config.exceptions.CartelaException;
import br.com.amaro.SIF.dto.NovaCartelaDTO;
import br.com.amaro.SIF.dto.NovoSeloDTO;
import br.com.amaro.SIF.form.NovaCartelaForm;
import br.com.amaro.SIF.form.NovoSeloForm;
import br.com.amaro.SIF.models.Cartela;
import br.com.amaro.SIF.models.ModeloCartela;
import br.com.amaro.SIF.models.Usuario;
import br.com.amaro.SIF.repository.CartelaRepository;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class CartelaService {

    @Value("${sif.config.cartela.serie}")
    private String cartelaSerie;
    @Value("${sif.config.mensagens.cartelacheia}")
    private String carteiraCheiaMessage;
    @Value("${sif.config.mensagens.qtdmaiorquepermitida}")
    private String qtdMaiorQuePermitida;
    @Autowired
    private CartelaRepository cartelaRepository;
    @Autowired
    private ModeloCartelaRepository modeloCartelaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public NovaCartelaDTO novaCartela(NovaCartelaForm form) {
        Optional<ModeloCartela> modeloCartelaOpt = modeloCartelaRepository.findById(form.getIdModelo());
        if(!modeloCartelaOpt.isPresent()) {
            throw new CartelaException("Modelo de cartela não existe!");
        }
        ModeloCartela modeloCartela = modeloCartelaOpt.get();
        Usuario usuario = usuarioRepository.findUsuarioByUserName(form.getUsername()).get();
        Cartela cartela = new Cartela();
        cartela.setModeloCartela(modeloCartela);
        cartela.setOwner(usuario);
        cartela.setDataExpiracao(modeloCartela.getDataExpiracao());
        cartela.setSerie(gerarSerie());
        cartela = cartelaRepository.save(cartela);
        return new NovaCartelaDTO(cartela);
    }

    public NovoSeloDTO novoSelo(NovoSeloForm form) {
        Cartela cartela = cartelaRepository.findCartelaBySerie(form.getSerie());
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
