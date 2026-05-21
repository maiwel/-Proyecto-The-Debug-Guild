import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/peliculas', pathMatch: 'full' },
  {
    path: 'peliculas',
    loadChildren: () =>
      import('./features/peliculas/peliculas.routes')
        .then(m => m.PELICULAS_ROUTES)
  },
  { path: '**', redirectTo: '/peliculas' }
];