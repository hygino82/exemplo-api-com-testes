package br.dev.hygino.heroes.controller;

import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.service.HeroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hero")
public class HeroController {

    private final HeroService service;

    public HeroController(HeroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseHeroDTO>> findAllHeroes(Pageable pageable) {
        Page<ResponseHeroDTO> page = service.findAllHeroes(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
