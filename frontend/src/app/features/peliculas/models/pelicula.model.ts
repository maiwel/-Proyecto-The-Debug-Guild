export interface Genero {
  id: number;
  nombre: string;
}

export interface Pelicula {
  id: number;
  titulo: string;
  anyo: number;
  director: string;
  generoId: number;
}