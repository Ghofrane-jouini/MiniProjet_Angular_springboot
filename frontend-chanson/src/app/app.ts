import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Auth } from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {

  protected readonly title = signal('MesChansons');

  constructor(
    public authService: Auth,
    private router: Router,
  ) {}

  ngOnInit() {
    this.authService.loadToken();
    const currentUrl = this.router.url;
    const publicRoutes = ['/login', '/register', '/verifEmail'];
    const isPublic = publicRoutes.some(route => currentUrl.includes(route));

    if (!isPublic && (!this.authService.getToken() || this.authService.isTokenExpired())) {
      this.router.navigate(['/login']);
    }
  }

  onLogout() {
    this.authService.logout();
  }
}