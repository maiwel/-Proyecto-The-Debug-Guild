export interface Pelicula {
    id: number;
    titulo: string;
    director: string;
    genero: string;
    anio: number;
    duracion: number; // en minutos
    sinopsis: string;
    puntuacion: number; //0-10
    imagen?: string;
    disponible: boolean;
}