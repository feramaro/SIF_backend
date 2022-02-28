package br.com.amaro.SIF.controllers;


import br.com.amaro.SIF.dto.NovaCartelaDTO;
import br.com.amaro.SIF.dto.NovoSeloDTO;
import br.com.amaro.SIF.form.NovaCartelaForm;
import br.com.amaro.SIF.form.NovoSeloForm;
import br.com.amaro.SIF.services.CartelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/sif_cartela")
public class CartelaController {

    @Autowired
    private CartelaService cartelaService;

    @PostMapping
    public ResponseEntity<NovaCartelaDTO> novaCartela(@RequestBody NovaCartelaForm form) {
       NovaCartelaDTO novaCartelaDTO = cartelaService.novaCartela(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCartelaDTO);
    }

    @PostMapping("/novoselo")
    public ResponseEntity<NovoSeloDTO> novoSelo(@RequestBody NovoSeloForm form) {
       NovoSeloDTO novoSeloDTO = cartelaService.novoSelo(form);
        return ResponseEntity.ok(novoSeloDTO);
    }
}
