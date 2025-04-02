package br.dev.hygino.heroes.dto;

import br.dev.hygino.heroes.model.Hero;

public record ResponseHeroDTO(
        Long id,
        String name,
        String codename,
        Integer age,
        String country,
        String team) {

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
