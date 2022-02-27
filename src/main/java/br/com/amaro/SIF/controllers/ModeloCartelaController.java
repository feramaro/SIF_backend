package br.com.amaro.SIF.controllers;

import br.com.amaro.SIF.dto.ModeloCartelaDTO;
import br.com.amaro.SIF.form.ModeloCartelaForm;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.services.ModeloCartelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sif_modelo")
public class ModeloCartelaController {

    @Autowired
    private ModeloCartelaService modeloCartelaService;

    @PostMapping
    private ResponseEntity<ModeloCartelaDTO> novoModelo(
            @RequestHeader("Authorization") String authorization, @RequestBody ModeloCartelaForm form) {
        ModeloCartelaDTO modeloCartelaDTO = modeloCartelaService.novaCartela(authorization, form);
        return ResponseEntity.ok(modeloCartelaDTO);
    }


}
