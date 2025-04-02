package br.dev.hygino.heroes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestHeroDTO(
        @NotBlank
        @Size(min = 3)
        String name,
        
        @NotBlank
        @Size(min = 3)
        String codename,
        
        @NotNull
        Integer age,
        
        @Size(min = 3)
        @NotBlank
        String country,
        
        @Size(min = 3)
        @NotBlank
        String team) {

}
