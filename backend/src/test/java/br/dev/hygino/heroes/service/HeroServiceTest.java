package br.dev.hygino.heroes.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.factory.HeroFactory;
import br.dev.hygino.heroes.model.Hero;
import br.dev.hygino.heroes.repository.HeroRepository;

@ExtendWith(SpringExtension.class)
public class HeroServiceTest {

    @InjectMocks
    private HeroService service;

    @Mock
    private HeroRepository repository;

    private long existingId;
    private long nonExistingId;
    //private long dependentId;
    private PageImpl<Hero> page;
    private Hero heroEntity;
    private ResponseHeroDTO responseHeroDTO;
    private RequestHeroDTO requestHeroDTO;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 2L;
        //dependentId = 3L;
        heroEntity = HeroFactory.createHeroEntity();
        page = new PageImpl<>(List.of(heroEntity));
        responseHeroDTO = HeroFactory.createInsertResponseHeroDTO();
        requestHeroDTO = HeroFactory.createRequestHeroDTO();

        when(repository.save(any())).thenReturn(heroEntity);

        doNothing().when(repository).deleteById(existingId);

        when(repository.findAll((Pageable) any())).thenReturn(page);

        when(repository.findById(existingId))
                .thenReturn(Optional.of(heroEntity));

        when(repository.findById(nonExistingId))
                .thenReturn(Optional.empty());

        when(repository.getReferenceById(existingId))
                .thenReturn(heroEntity);

        when(repository.save(HeroFactory.createUpdatedHeroEntity())).thenReturn(HeroFactory.createUpdatedHeroEntity());

    }

    @Test
    public void insertShouldReturnHero() {
        final var res = service.insertHero(requestHeroDTO);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(responseHeroDTO, res);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            service.removeHero(existingId);
        });

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void findAllHeroesShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ResponseHeroDTO> result = service.findAllHeroes(pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnHero() {
        final var res = service.findHeroById(existingId);
        Assertions.assertNotNull(res);
    }

    @Test
    @DisplayName("FindById deve lançar IllegalArgumentException quando o id não existir")
    public void findByIdShouldThrowIllegalArgumentExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.findHeroById(nonExistingId));
    }
}
