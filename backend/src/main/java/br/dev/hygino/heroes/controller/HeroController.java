package br.dev.hygino.heroes.controller;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.service.HeroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hero")
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHeroDTO> findHeroById(@PathVariable Long id) {
        try {
            ResponseHeroDTO res = service.findHeroById(id);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseHeroDTO> insertHero(@RequestBody @Valid RequestHeroDTO dto) {
        ResponseHeroDTO res = service.insertHero(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHero(@PathVariable Long id, @RequestBody @Valid RequestHeroDTO dto) {
        try {
            ResponseHeroDTO res = service.updateHero(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeHero(@PathVariable Long id) {
        service.removeHero(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
