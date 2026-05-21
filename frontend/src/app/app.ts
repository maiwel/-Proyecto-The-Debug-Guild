import { Component } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule],
  template: `
    <nav>
      <a routerLink="/peliculas">🎬 Películas</a>
    </nav>
    <router-outlet />
  `
})
export class AppComponent {
  title = 'frontend';
}