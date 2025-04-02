package br.dev.hygino.heroes.service;

import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.model.Hero;
import br.dev.hygino.heroes.repository.HeroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeroService {

    private final HeroRepository repository;

    public HeroService(HeroRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ResponseHeroDTO> findAllHeroes(Pageable pageable) {
        final Page<Hero> page = repository.findAll(pageable);
        return page.map(ResponseHeroDTO::new);
    }

    @Transactional(readOnly = true)
    public ResponseHeroDTO findHeroById(Long id) {
        Hero res = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe heroi com o id: " + id));

        return new ResponseHeroDTO(res);
    }
}
