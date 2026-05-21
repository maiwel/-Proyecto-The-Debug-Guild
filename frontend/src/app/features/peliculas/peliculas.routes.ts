import { Routes } from '@angular/router';

export const PELICULAS_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./components/pelicula-list/pelicula-list')
        .then(m => m.PeliculaList)
  },
  {
    path: 'nueva',
    loadComponent: () =>
      import('./components/pelicula-form/pelicula-form')
        .then(m => m.PeliculaForm)
  },
  {
    path: ':id',
    loadComponent: () =>
      import('./components/pelicula-detail/pelicula-detail')
        .then(m => m.PeliculaDetail)
  }
];