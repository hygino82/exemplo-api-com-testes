import {Routes} from '@angular/router';
import {HeroesComponent} from './page/heroes/heroes.component';
import {FormComponent} from './page/form/form.component';

export const routes: Routes = [
  {
    path: 'heroes', component: HeroesComponent
  }, {
    path: 'form', component: FormComponent
  }
];
