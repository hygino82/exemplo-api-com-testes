import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Hero } from '../../../types/Hero';
import {HeroesService} from '../../services/heroes.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  heroes: Hero[] = [];

  constructor(private service: HeroesService) {}

  ngOnInit(): void {
    this.service.getLista().subscribe(
      (data) => {
        this.heroes = data.content;
        console.log('Lista recebida:', this.heroes);
      },
      (error) => {
        console.error('Erro ao buscar a lista:', error);
      }
    );
  }


  Remover(id: number) {
    this.heroes = this.heroes.filter(x => x.id !== id);
  }

  Informacao(id: number) {
    const hero = this.heroes.find(x => x.id === id);
    if (hero) {
      alert(hero.name);
    }
  }

  @Output() Adicionar = new EventEmitter<Hero>();

  adicionarHero(hero: Hero) {
    this.Adicionar.emit(hero);
  }
}
