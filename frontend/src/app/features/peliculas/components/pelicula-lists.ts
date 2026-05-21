import { Component, inject, signal } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { PeliculaCard } from '../components/pelicula-card';
import { PeliculaService } from '../services/pelicula.service';
import { Pelicula } from '../models/pelicula.model';

@Component({
  selector: 'app-pelicula-list',
  imports: [RouterModule, PeliculaCard],
  templateUrl: './pelicula-list.html',
  styleUrl: './pelicula-list.css'
})
export class PeliculaList {
  private peliculaService = inject(PeliculaService);
  private router = inject(Router);

  peliculas = signal<Pelicula[]>([]);
  peliculaSeleccionada = signal<Pelicula | null>(null);

  constructor() {
    this.peliculaService.getPeliculas().subscribe(
      peliculas => this.peliculas.set(peliculas)
    );
  }

  onPeliculaSelected(pelicula: Pelicula) {
    this.router.navigate(['/peliculas', pelicula.id]);
  }
}
  /*<h1>🎬 Catálogo de Películas</h1>

@if (peliculaSeleccionada()) {
  <div class="detalle-seleccionada">
    <h2>{{ peliculaSeleccionada()!.titulo }}</h2>
    <p><strong>Director:</strong> {{ peliculaSeleccionada()!.director }}</p>
    <p><strong>Género:</strong> {{ peliculaSeleccionada()!.genero }}
       ({{ peliculaSeleccionada()!.anio }})</p>
    <p><strong>Sinopsis:</strong> {{ peliculaSeleccionada()!.sinopsis }}</p>
    <p>⭐ {{ peliculaSeleccionada()!.puntuacion }}/10</p>
    <button (click)="cerrarDetalle()">✕ Cerrar</button>
  </div>
}

<div class="peliculas-grid">
  @for (pelicula of peliculas(); track pelicula.id) {
    <app-pelicula-card
      [pelicula]="pelicula"
      (selected)="onPeliculaSelected($event)"
    />
  } @empty {
    <p>No hay películas disponibles</p>
  }
</div>*/
