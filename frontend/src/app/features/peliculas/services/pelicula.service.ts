import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Pelicula } from '../models/pelicula.model';

@Injectable({ providedIn: 'root' })
export class PeliculaService {
  private http = inject(HttpClient);
  private peliculasMock: Pelicula[] = [/* datos */];

  getPeliculas(): Observable<Pelicula[]> {
    return of(this.peliculasMock);
  }

  getPelicula(id: number): Observable<Pelicula | undefined> {
    return of(this.peliculasMock.find(p => p.id === id));
  }

  buscar(termino: string): Observable<Pelicula[]> {
    const resultado = this.peliculasMock.filter(p =>
      p.titulo.toLowerCase().includes(termino.toLowerCase())
    );
    return of(resultado);
  }

  crear(pelicula: Omit<Pelicula, 'id'>): Observable<Pelicula> {
    const nueva = { ...pelicula, id: Date.now() } as Pelicula;
    this.peliculasMock.push(nueva);
    return of(nueva);
  }
}