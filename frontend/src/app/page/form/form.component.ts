import { Component } from '@angular/core';
import { HeroesService } from '../../services/heroes.service';
import {Hero, InsertHero} from '../../../types/Hero';

@Component({
  selector: 'app-heroes',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  constructor(private heroesService: HeroesService) {}

  cadastrar(event: Event) {
    event.preventDefault(); // Evita recarregar a página

    const target = event.target as HTMLFormElement;
    const hero:InsertHero = {
      name: (target.elements.namedItem('name') as HTMLInputElement).value,
      codename: (target.elements.namedItem('codename') as HTMLInputElement).value,
      age: Number((target.elements.namedItem('age') as HTMLInputElement).value),
      country: (target.elements.namedItem('country') as HTMLInputElement).value,
      team: (target.elements.namedItem('team') as HTMLInputElement).value
    };

    this.heroesService.cadastrarHeroi(hero).subscribe(
      (response) => {
        alert('Herói cadastrado com sucesso!');
        target.reset(); // Limpa o formulário
      },
      (error) => {
        alert('Erro ao cadastrar herói!');
        console.error(error);
      }
    );
  }
}
