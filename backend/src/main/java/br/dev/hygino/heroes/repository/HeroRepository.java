package br.dev.hygino.heroes.repository;

import br.dev.hygino.heroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {

}
