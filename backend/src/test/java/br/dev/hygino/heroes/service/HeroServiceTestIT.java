package br.dev.hygino.heroes.service;

import br.dev.hygino.heroes.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.factory.HeroFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HeroServiceTestIT {

    private long existingId;
    private long nonExistingId;
    private long countTotalHeroes;
    private ResponseHeroDTO responseDto;
    private RequestHeroDTO requestDto;

    @BeforeEach
    public void setUp() {
        existingId = 5L;
        nonExistingId = 1000L;
        countTotalHeroes = 10L;
        responseDto = HeroFactory.createResponseHeroDTO();
        requestDto = HeroFactory.createRequestHeroDTO();
    }

    @Autowired
    private HeroService service;

    @Autowired
    private HeroRepository repository;

    @Test
    @DisplayName("findHeroById deve retornar um ResponseHeroDTO se o id existir")
    public void findHeroByIdShouldReturnHeroWhenIdExists() {
        final ResponseHeroDTO res = service.findHeroById(existingId);
        assertNotNull(res);
        assertEquals(responseDto, res);
    }

    @Test
    @DisplayName("findHeroById deve lançar IllegalArgumentException quando o id não existir")
    public void findHeroByIdShouldThrowIllegalArgumentExceptionWhenIdDoesNotExists() {
        assertThrows(IllegalArgumentException.class, () -> service.findHeroById(nonExistingId));
    }

    @Test
    @DisplayName("insertHero deve retornar um ResponseHeroDTO se os valores inseridos forem válidos")
    public void insertHero() {
        final ResponseHeroDTO res = service.insertHero(requestDto);
        assertNotNull(res);
        assertNotNull(res.id());
    }

    @Test
    @DisplayName("updateHero deve retornar um ResponseHeroDTO se os valores inseridos forem válidos")
    public void updateHero() {
        final ResponseHeroDTO res = service.updateHero(existingId, requestDto);
        assertNotNull(res);
        assertEquals(existingId, res.id());
        assertEquals("John Stuart", res.name());

    }


    @Test
    @DisplayName("findAllHeroes deve retornar uma página com dez elementos")
    public void findAllHeroesShouldReturnPageWhenPage0Size10() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ResponseHeroDTO> result = service.findAllHeroes(pageRequest);

        assertFalse(result.isEmpty());
        assertEquals(0, result.getNumber());
        assertEquals(10, result.getSize());
        assertEquals(countTotalHeroes, result.getTotalElements());
        assertEquals("Tony Stark", result.getContent().get(0).name());
    }

    @Test
    @DisplayName("findAllHeroes deve retornar uma página vazia quando a página não existir")
    public void findAllHeroesShouldReturnEmptyPageWhenPageNoExists() {
        PageRequest pageRequest = PageRequest.of(50, 10);
        Page<ResponseHeroDTO> result = service.findAllHeroes(pageRequest);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("findAllHeroes deve retornar uma página de heróis ordenada por nome")
    public void findAllHeroesShouldReturnSortedPageWhenSortByName() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));

        Page<ResponseHeroDTO> result = service.findAllHeroes(pageRequest);

        assertFalse(result.isEmpty());
        assertEquals("Arthur Curry", result.getContent().get(0).name());
        assertEquals("Barry Allen", result.getContent().get(1).name());
        assertEquals("Bruce Banner", result.getContent().get(2).name());
    }

    @Test
    @DisplayName("removeHero deve remover o herói se o id existir")
    public void removeHeroShouldDeleteResourceWhenIdExists() {
        service.removeHero(existingId);
        // verifica se foi removido 1 elemento após usar o delete
        assertEquals(countTotalHeroes - 1, repository.count());
    }
}
