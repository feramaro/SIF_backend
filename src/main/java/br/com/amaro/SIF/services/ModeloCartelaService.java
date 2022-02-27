package br.com.amaro.SIF.services;

import br.com.amaro.SIF.dto.ModeloCartelaDTO;
import br.com.amaro.SIF.form.ModeloCartelaForm;
import br.com.amaro.SIF.form.ModeloCartelaUpdateForm;
import br.com.amaro.SIF.models.ModeloCartela;
import br.com.amaro.SIF.models.Usuario;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;

@Service
public class ModeloCartelaService {

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

}
