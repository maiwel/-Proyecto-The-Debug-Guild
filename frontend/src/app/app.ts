import { Component, signal } from '@angular/core';
import { Pelicula } from '../../models/pelicula.model';
import { PeliculaCard } from '../pelicula-card/pelicula-card';


@Component({
  selector: 'app-pelicula-list',
  imports: [PeliculaCard], // ← Importación directa
  template: `
    <h1>🎬 Catálogo de Películas</h1>
    <div class="peliculas-grid">
      @for (pelicula of peliculas(); track pelicula.id) {
        <app-pelicula-card
          [pelicula]="pelicula"
          (selected)="onPeliculaSelected($event)" />
      } @empty {
        <p>No hay películas disponibles</p>
      }
    </div>
  `
})
export class PeliculaList {
  peliculas = signal<Pelicula[]>([/* datos mock */]);

  onPeliculaSelected(pelicula: Pelicula) {
    console.log('Seleccionada:', pelicula.titulo);
  }
}