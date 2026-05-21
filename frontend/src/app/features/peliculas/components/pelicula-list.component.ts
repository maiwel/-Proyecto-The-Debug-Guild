import { Component, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PeliculaService } from '../services/pelicula.service';
import { Pelicula } from '../models/pelicula.model';

@Component({
  selector: 'app-pelicula-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  template: `
    <div class="container">
      <h1>🎬 Listado de Películas</h1>
      <div class="acciones">
        <input
          type="text"
          [value]="busqueda()"
          (input)="busqueda.set($any($event.target).value)"
          placeholder="🔍 Buscar película..."
        />
        <a routerLink="/peliculas/nueva" class="btn-nuevo">+ Añadir película</a>
      </div>
      <table>
        <thead>
          <tr>
            <th>Título</th>
            <th>Año</th>
            <th>Director</th>
            <th>Género</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          @for (pelicula of peliculasFiltradas(); track pelicula.id) {
            <tr>
              <td>{{ pelicula.titulo }}</td>
              <td>{{ pelicula.anyo }}</td>
              <td>{{ pelicula.director }}</td>
              <td>{{ pelicula.genero.nombre }}</td>
              <td><button class="btn-borrar" (click)="borrar(pelicula.id)">🗑️</button></td>
            </tr>
          }
        </tbody>
      </table>
    </div>
  `,
  styles: [`
    .acciones {
      display: flex;
      gap: 16px;
      align-items: center;
      margin-bottom: 16px;
    }
    .acciones input {
      flex: 1;
      padding: 10px 14px;
      border-radius: 6px;
      border: 1px solid #0f3460;
      background-color: #1a1a2e;
      color: #eee;
      font-size: 15px;
    }
    .btn-nuevo {
      background-color: #e94560;
      color: white;
      padding: 10px 20px;
      border-radius: 6px;
      font-weight: bold;
      white-space: nowrap;
    }
    .btn-borrar {
      background: none;
      border: none;
      cursor: pointer;
      font-size: 18px;
      padding: 4px 8px;
    }
    .btn-borrar:hover {
      transform: scale(1.2);
    }
  `]
})
export class PeliculaListComponent implements OnInit {
  peliculas = signal<Pelicula[]>([]);
  busqueda = signal('');

  peliculasFiltradas = computed(() => {
    const texto = this.busqueda().toLowerCase();
    return this.peliculas().filter(p =>
      p.titulo.toLowerCase().includes(texto) ||
      p.director.toLowerCase().includes(texto) ||
      p.genero.nombre.toLowerCase().includes(texto)
    );
  });

  constructor(private peliculaService: PeliculaService) {}

  ngOnInit() {
    this.peliculaService.getPeliculas().subscribe(data => {
      const ordenadas = data.sort((a, b) => a.titulo.localeCompare(b.titulo));
      this.peliculas.set(ordenadas);
    });
  }

  borrar(id: number) {
    if (confirm('¿Seguro que quieres borrar esta película?')) {
      this.peliculaService.borrarPelicula(id).subscribe(() => {
        this.peliculas.update(lista => lista.filter(p => p.id != id));
      });
    }
  }
}