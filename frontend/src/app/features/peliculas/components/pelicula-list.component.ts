import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PeliculaService } from '../services/pelicula.service';
import { Pelicula } from '../models/pelicula.model';

@Component({
  selector: 'app-pelicula-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="container">
      <h1>🎬 Listado de Películas</h1>
      <a routerLink="/peliculas/nueva">+ Añadir película</a>
      <table>
        <thead>
          <tr>
            <th>Título</th>
            <th>Año</th>
            <th>Director</th>
          </tr>
        </thead>
        <tbody>
          @for (pelicula of peliculas(); track pelicula.id) {
            <tr>
              <td>{{ pelicula.titulo }}</td>
              <td>{{ pelicula.anyo }}</td>
              <td>{{ pelicula.director }}</td>
            </tr>
          }
        </tbody>
      </table>
    </div>
  `
})
export class PeliculaListComponent implements OnInit {
  peliculas = signal<Pelicula[]>([]);

  constructor(private peliculaService: PeliculaService) {}

  ngOnInit() {
    this.peliculaService.getPeliculas().subscribe(data => {
      this.peliculas.set(data);
    });
  }
}