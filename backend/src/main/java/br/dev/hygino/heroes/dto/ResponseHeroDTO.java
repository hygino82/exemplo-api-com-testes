package br.dev.hygino.heroes.dto;

import br.dev.hygino.heroes.model.Hero;
import java.io.Serializable;

public record ResponseHeroDTO(
        Long id,
        String name,
        String codename,
        Integer age,
        String country,
        String team) implements Serializable{

    public ResponseHeroDTO(Hero entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getCodename(),
                entity.getAge(),
                entity.getCountry(),
                entity.getTeam());
    }
}
