package br.com.amaro.SIF.services;

import br.com.amaro.SIF.dto.ModeloCartelaDTO;
import br.com.amaro.SIF.form.ModeloCartelaForm;
import br.com.amaro.SIF.form.ModeloCartelaUpdateForm;
import br.com.amaro.SIF.repository.models.ModeloCartela;
import br.com.amaro.SIF.repository.models.Usuario;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.repository.UsuarioRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class ModeloCartelaService {

    @Value("${sif.config.modelo.convite.intervaloA}")
    private String intervaloA;
    @Value("${sif.config.modelo.convite.intervaloB}")
    private String intervaloB;
    @Value("${sif.config.modelo.convite.letrasAGerar}")
    private String letrasAGerar;

    @Autowired
    private ModeloCartelaRepository modeloCartelaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    public ModeloCartelaDTO novaCartela(String authorization, ModeloCartelaForm form) {
        Long userId = tokenService.convertTokenAndGetId(authorization);
        Usuario usuario = usuarioRepository.getById(userId);
        ModeloCartela modeloCartela = form.toModeloCartela();
        modeloCartela.setDataExpiracao(geraDataExpiracao(form.getDiasExpiracao()));
        modeloCartela.setCriador(usuario);
        modeloCartela.setCodigoConvite(geraCodigoConvite());
        modeloCartelaRepository.save(modeloCartela);
        return new ModeloCartelaDTO(modeloCartela);
    }

    public ModeloCartelaDTO atualizaModelo(Long id, ModeloCartelaUpdateForm form) {
        ModeloCartela modeloCartela = modeloCartelaRepository.getById(id);
        modeloCartela.setAtiva(form.isAtiva());
        modeloCartela = modeloCartelaRepository.save(modeloCartela);
        return new ModeloCartelaDTO(modeloCartela);
    }

    private Date geraDataExpiracao(Integer numeroDias) {
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DATE, numeroDias);
        data = cal.getTime();
        return data;
    }

    private String geraCodigoConvite() {
        Long numberStart = RandomUtils.nextLong(Long.parseLong(intervaloA), Long.parseLong(intervaloB));
        Long numberEnd = RandomUtils.nextLong(Long.parseLong(intervaloA), Long.parseLong(intervaloB));
        String letras = RandomStringUtils.randomAlphabetic(Integer.parseInt(letrasAGerar)).toUpperCase();
        return String.format("%s%d%d", letras,numberStart, numberEnd);
    }

}
