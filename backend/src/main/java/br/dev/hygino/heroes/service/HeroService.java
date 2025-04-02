package br.dev.hygino.heroes.service;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.model.Hero;
import br.dev.hygino.heroes.repository.HeroRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public ResponseHeroDTO insertHero(RequestHeroDTO dto) {
        Hero entity = new Hero();
        dtoToEntity(dto, entity);
        entity = repository.save(entity);

        return new ResponseHeroDTO(entity);
    }

    private void dtoToEntity(RequestHeroDTO dto, Hero entity) {
        entity.setName(dto.name());
        entity.setCodename(dto.codename());
        entity.setAge(dto.age());
        entity.setCountry(dto.country());
        entity.setTeam(dto.team());
    }

    @Transactional
    public ResponseHeroDTO updateHero(Long id, RequestHeroDTO dto) {
        try {
            Hero entity = repository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = repository.save(entity);

            return new ResponseHeroDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Não existe heroi com o id: " + id);
        }
    }

    public void removeHero(Long id) {
        repository.deleteById(id);
    }
}
