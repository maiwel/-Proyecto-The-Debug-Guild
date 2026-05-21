import { Routes } from '@angular/router';
import { PeliculaListComponent } from './features/peliculas/components/pelicula-list.component';
import { PeliculaFormComponent } from './features/peliculas/components/pelicula-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'peliculas', pathMatch: 'full' },
  { path: 'peliculas', component: PeliculaListComponent },
  { path: 'peliculas/nueva', component: PeliculaFormComponent }
];