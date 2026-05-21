import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pelicula, Genero } from '../models/pelicula.model';

@Injectable({
  providedIn: 'root'
})
export class PeliculaService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getGeneros(): Observable<Genero[]> {
    return this.http.get<Genero[]>(`${this.apiUrl}/generos`);
  }

  getPeliculas(): Observable<Pelicula[]> {
    return this.http.get<Pelicula[]>(`${this.apiUrl}/peliculas`);
  }

  getPelicula(id: number): Observable<Pelicula> {
    return this.http.get<Pelicula>(`${this.apiUrl}/peliculas/${id}`);
  }

  crearPelicula(pelicula: any): Observable<Pelicula> {
    return this.http.post<Pelicula>(`${this.apiUrl}/peliculas`, pelicula);
  }

  borrarPelicula(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/peliculas/${id}`);
  }
}