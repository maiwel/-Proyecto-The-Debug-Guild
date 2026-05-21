import {Component, input, output} from '@angular/core';
import {Pelicula} from '../models/pelicula.model';  
import {HighlightDirective} from '../directives/highlight.directive';
import { DuracionPipe } from '../shared/pipes/duration.pipe'; // ← Importación del pipe

@Component({
    selector: 'app-pelicula-card',
    imports: [HighlightDirective], // ← Importación directa
    template: `
       <div class="pelicula-card" [appHighlight]="'lightblue'">
  <h3>{{ pelicula().titulo }}</h3>
  <p>🎬 {{ pelicula().director }}</p>
  <p>{{ pelicula().genero }} ({{ pelicula().anio }})</p>
  <p>⭐ {{ pelicula().puntuacion }}/10</p>
  <button (click)="selected.emit(pelicula())">Ver detalle</button>
</div>
    `
})      

export class PeliculaCard {
    pelicula = input.required<Pelicula>();
    selected = output<Pelicula>();
}
