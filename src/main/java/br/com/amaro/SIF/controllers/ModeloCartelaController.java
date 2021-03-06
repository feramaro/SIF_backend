package br.com.amaro.SIF.controllers;

import br.com.amaro.SIF.dto.ModeloCartelaDTO;
import br.com.amaro.SIF.form.ModeloCartelaForm;
import br.com.amaro.SIF.form.ModeloCartelaUpdateForm;
import br.com.amaro.SIF.repository.ModeloCartelaRepository;
import br.com.amaro.SIF.services.ModeloCartelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sif_modelo")
public class ModeloCartelaController {

    @Autowired
    private ModeloCartelaService modeloCartelaService;

    @PostMapping
    private ResponseEntity<ModeloCartelaDTO> novoModelo(
            @RequestHeader("Authorization") String authorization, @Valid @RequestBody ModeloCartelaForm form) {
        ModeloCartelaDTO modeloCartelaDTO = modeloCartelaService.novaCartela(authorization, form);
        return ResponseEntity.ok(modeloCartelaDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ModeloCartelaDTO> atualizaModelo(
            @PathVariable Long id, @Valid @RequestBody ModeloCartelaUpdateForm form) {
        ModeloCartelaDTO modelo = modeloCartelaService.atualizaModelo(id, form);
        return ResponseEntity.ok(modelo);
    }


}
