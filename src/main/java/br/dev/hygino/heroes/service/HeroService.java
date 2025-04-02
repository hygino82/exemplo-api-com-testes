package br.dev.hygino.heroes.service;

import br.dev.hygino.heroes.repository.HeroRepository;
import org.springframework.stereotype.Service;

@Service
public class HeroService {
    
    private final HeroRepository repository;

    public HeroService(HeroRepository repository) {
        this.repository = repository;
    }
    
    
}
