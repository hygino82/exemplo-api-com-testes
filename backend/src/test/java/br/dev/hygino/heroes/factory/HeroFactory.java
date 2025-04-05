package br.dev.hygino.heroes.factory;

import br.dev.hygino.heroes.dto.RequestHeroDTO;
import br.dev.hygino.heroes.dto.ResponseHeroDTO;

public class HeroFactory {

	public static ResponseHeroDTO createResponseHeroDTO() {
		return new ResponseHeroDTO(5L, "Peter Parker", "Homem-Aranha", 19, "USA", "Avengers");
	}

	public static RequestHeroDTO createRequestHeroDTO() {
		return new RequestHeroDTO("John Stuart", "Green Lantern", 35, "USA", "Justice League");
	}
}
