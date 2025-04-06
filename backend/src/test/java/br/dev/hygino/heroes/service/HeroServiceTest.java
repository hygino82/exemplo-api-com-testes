package br.dev.hygino.heroes.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
	private long dependentId;
	private PageImpl<Hero> page;
	private Hero heroEntity;
	private ResponseHeroDTO responseHeroDTO;
	private RequestHeroDTO requestHeroDTO;

	@BeforeEach
	public void setUp() {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		heroEntity = HeroFactory.createHeroEntity();
		page = new PageImpl<>(List.of(heroEntity));
		responseHeroDTO = HeroFactory.createInsertResponseHeroDTO();
		requestHeroDTO = HeroFactory.createRequestHeroDTO();

		Mockito.when(repository.save(any())).thenReturn(heroEntity);

		Mockito.doNothing().when(repository).deleteById(existingId);
		
		Mockito.when(repository.findAll((Pageable) any())).thenReturn(page);

	}

	@Test
	public void insertShoudReturnHero() {
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

}
