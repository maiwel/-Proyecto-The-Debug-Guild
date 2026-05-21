import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PeliculaService } from '../services/pelicula.service';
import { Genero } from '../models/pelicula.model';

@Component({
  selector: 'app-pelicula-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="container">
      <h1>➕ Nueva Película</h1>
      <form [formGroup]="form" (ngSubmit)="guardar()">
        <label>Título</label>
        <input formControlName="titulo" placeholder="Título de la película" />

        <label>Año</label>
        <input formControlName="anyo" type="number" placeholder="Año" />

        <label>Director</label>
        <input formControlName="director" placeholder="Director" />

        <label>Género</label>
        <select formControlName="generoId">
          @for (genero of generos(); track genero.id) {
            <option [value]="genero.id">{{ genero.nombre }}</option>
          }
        </select>

        <button type="submit" [disabled]="form.invalid">Guardar</button>
        <button type="button" (click)="cancelar()">Cancelar</button>
      </form>
    </div>
  `
})
export class PeliculaFormComponent implements OnInit {
  form: FormGroup;
  generos = signal<Genero[]>([]);

  constructor(
    private fb: FormBuilder,
    private peliculaService: PeliculaService,
    private router: Router
  ) {
    this.form = this.fb.group({
      titulo: ['', Validators.required],
      anyo: ['', Validators.required],
      director: ['', Validators.required],
      generoId: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.peliculaService.getGeneros().subscribe(data => {
      this.generos.set(data);
    });
  }

  guardar() {
    if (this.form.valid) {
      this.peliculaService.crearPelicula(this.form.value).subscribe(() => {
        this.router.navigate(['/peliculas']);
      });
    }
  }

  cancelar() {
    this.router.navigate(['/peliculas']);
  }
}