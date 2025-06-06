package br.dev.hygino.heroes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 100)
    private String codename;

    @NotNull
    private Integer age;

    @NotBlank
    @Size(min = 3, max = 100)
    private String country;

    @NotBlank
    @Size(min = 3, max = 100)
    private String team;

    public Hero() {
    }

    public Hero(Long id, 
    		@NotBlank @Size(min = 3, max = 100) String name,
			@NotBlank @Size(min = 3, max = 100) String codename, 
			@NotNull Integer age,
			@NotBlank @Size(min = 3, max = 100) String country,
			@NotBlank @Size(min = 3, max = 100) String team) {
		this.id = id;
		this.name = name;
		this.codename = codename;
		this.age = age;
		this.country = country;
		this.team = team;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
