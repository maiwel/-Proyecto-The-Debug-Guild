import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { PeliculaService } from '../services/pelicula.service';
import { DuracionPipe } from '../../../shared/pipes/duration.pipe';
import { Pelicula } from '../models/pelicula.model';

@Component({
  selector: 'app-pelicula-detail',
  imports: [RouterLink, DuracionPipe],
  templateUrl: './pelicula-detail.html',
  styleUrl: './pelicula-detail.css'
})
export class PeliculaDetail {
  private route = inject(ActivatedRoute);
  private peliculaService = inject(PeliculaService);

  pelicula = signal<Pelicula | null>(null);

  constructor() {
    this.route.params.pipe(
      switchMap(params => this.peliculaService.getPelicula(+params['id']))
    ).subscribe(pelicula => this.pelicula.set(pelicula ?? null));
  }
}