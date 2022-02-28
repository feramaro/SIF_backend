package br.com.amaro.SIF.controllers;


import br.com.amaro.SIF.dto.NovaCartelaDTO;
import br.com.amaro.SIF.dto.NovoSeloDTO;
import br.com.amaro.SIF.dto.OwnerCartelasDTO;
import br.com.amaro.SIF.form.ConviteForm;
import br.com.amaro.SIF.form.NovaCartelaForm;
import br.com.amaro.SIF.form.NovoSeloForm;
import br.com.amaro.SIF.services.CartelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
    @RequestMapping("/sif_cartela")
public class CartelaController {

    @Autowired
    private CartelaService cartelaService;

    @PostMapping
    public ResponseEntity<NovaCartelaDTO> novaCartela(@Valid @RequestBody NovaCartelaForm form) {
       NovaCartelaDTO novaCartelaDTO = cartelaService.novaCartela(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCartelaDTO);
    }

    @PostMapping("/novoselo")
    public ResponseEntity<NovoSeloDTO> novoSelo(
            @RequestHeader("Authorization") String authorization, @Valid @RequestBody NovoSeloForm form) {
       NovoSeloDTO novoSeloDTO = cartelaService.novoSelo(authorization, form);
        return ResponseEntity.ok(novoSeloDTO);
    }

    @GetMapping("/minhas")
    public ResponseEntity<OwnerCartelasDTO> getOwnerCartelas(
            @RequestHeader("Authorization") String authorization) {
        OwnerCartelasDTO ownerCartelasDTO = cartelaService.buscaCartelasUsuario(authorization);
        return ResponseEntity.ok(ownerCartelasDTO);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<NovaCartelaDTO> adicionaPorConvite(
            @RequestHeader("Authorization") String authorization, @Valid @RequestBody ConviteForm form) {
        NovaCartelaDTO novaCartelaDTO = cartelaService.adicionaPorConvite(authorization, form);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCartelaDTO);
    }
}
