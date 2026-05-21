import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'duracion'
})
export class DuracionPipe implements PipeTransform {
  transform(minutos: number): string {
    if (!minutos || minutos <= 0) return '0min';
    const horas = Math.floor(minutos / 60);
    const mins = minutos % 60;
    return horas > 0 ? `${horas}h ${mins}min` : `${mins}min`;
  }
}
// Ejemplo: {{ 148 | duracion }} → "2h 28min"