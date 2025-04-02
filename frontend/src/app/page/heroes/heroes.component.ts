import { Component } from '@angular/core';
import {Hero} from '../../../types/Hero';

@Component({
  selector: 'app-heroes',
  imports: [],
  templateUrl: './heroes.component.html',
  styleUrl: './heroes.component.css'
})
export class HeroesComponent {
  herois: Hero[] = [
    {
      name: 'Peter Parker',
      codename: "Spiderman",
      age: 36,
      country: 'USA',
      team: 'Avengers',
      id: 1
    },
    {
      name: 'Tony Stark',
      codename: "Iron Man",
      age: 48,
      country: 'USA',
      team: 'Avengers',
      id: 2
    },
    {
      name: 'Steve Rogers',
      codename: "Captain America",
      age: 105,
      country: 'USA',
      team: 'Avengers',
      id: 3
    },
    {
      name: 'Bruce Banner',
      codename: "Hulk",
      age: 50,
      country: 'USA',
      team: 'Avengers',
      id: 4
    },
    {
      name: 'Thor Odinson',
      codename: "Thor",
      age: 1500,
      country: 'Asgard',
      team: 'Avengers',
      id: 5
    },
    {
      name: 'Natasha Romanoff',
      codename: "Black Widow",
      age: 39,
      country: 'Russia',
      team: 'Avengers',
      id: 6
    },
    {
      name: 'Clark Kent',
      codename: "Superman",
      age: 35,
      country: 'Krypton/USA',
      team: 'Justice League',
      id: 7
    },
    {
      name: 'Bruce Wayne',
      codename: "Batman",
      age: 40,
      country: 'USA',
      team: 'Justice League',
      id: 8
    },
    {
      name: 'Diana Prince',
      codename: "Wonder Woman",
      age: 5000,
      country: 'Themyscira',
      team: 'Justice League',
      id: 9
    },
    {
      name: 'Barry Allen',
      codename: "The Flash",
      age: 32,
      country: 'USA',
      team: 'Justice League',
      id: 10
    },
    {
      name: 'Arthur Curry',
      codename: "Aquaman",
      age: 38,
      country: 'Atlantis',
      team: 'Justice League',
      id: 11
    },
    {
      name: 'Hal Jordan',
      codename: "Green Lantern",
      age: 39,
      country: 'USA',
      team: 'Justice League',
      id: 12
    }
  ];

  Remover(id: number) {
    this.herois = this.herois.filter(x => x.id !== id);
  }

  Informacao(id: number) {
    const res = this.herois.filter(x => x.id == id)[0];

    alert(res.name)
  }
}
