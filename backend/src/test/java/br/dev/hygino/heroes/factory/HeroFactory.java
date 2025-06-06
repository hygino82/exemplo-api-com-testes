package br.dev.hygino.heroes.factory;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;
import br.dev.hygino.heroes.model.Hero;

public class HeroFactory {

	public static ResponseHeroDTO createResponseHeroDTO() {
		return new ResponseHeroDTO(5L, "Peter Parker", "Homem-Aranha", 19, "USA", "Avengers");
	}

	public static RequestHeroDTO createRequestHeroDTO() {
		return new RequestHeroDTO("John Stuart", "Green Lantern", 35, "USA", "Justice League");
	}

	public static Hero createHeroEntity() {
		return new Hero(1L, "John Stuart", "Green Lantern", 35, "USA", "Justice League");
	}

	public static Hero createUpdatedHeroEntity() {
		return new Hero(1L, "John Stuart Updated", "Green Lantern Updated", 99, "USA", "Justice League");
	}

	public static RequestHeroDTO createUpdatedRequestHeroDTO() {
		return new RequestHeroDTO("John Stuart Updated", "Green Lantern Updated", 99, "USA", "Justice League");
	}

	public static ResponseHeroDTO createInsertResponseHeroDTO() {
		return new ResponseHeroDTO(1L, "John Stuart", "Green Lantern", 35, "USA", "Justice League");
	}


}
