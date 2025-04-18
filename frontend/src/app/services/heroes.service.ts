import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { PageRequest } from '../../types/PageRequest';
import {Hero, InsertHero} from '../../types/Hero';

@Injectable({
  providedIn: 'root'
})
export class HeroesService {

  private apiUrl = 'http://localhost:8080/api/hero';

  constructor(private http: HttpClient) { }

  getLista(page: number = 0, size: number = 15): Observable<PageRequest> {
    return this.http.get<PageRequest>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  cadastrarHeroi(hero: InsertHero): Observable<any> {
    return this.http.post<InsertHero>(this.apiUrl, hero);
  }
}
