import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Auth } from '../services/auth';
import { User } from '../model/user.model';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styles: ``
})
export class Login implements OnInit {

  err: number = 0;
  user = new User();
  message: string = 'Login ou mot de passe erronés..';

  constructor(private authService: Auth, private router: Router) {}

  ngOnInit(): void {}

  onLoggedin() {
    this.authService.login(this.user).subscribe({
      next: (data) => {
        let jwToken = data.headers.get('Authorization');
        if (jwToken) {
          this.authService.saveToken(jwToken);
          this.router.navigate(['/chansons']);
        } else {
          this.err = 1;
        }
      },
      error: (err) => {
        this.err = 1;
        if (err.error?.errorCause === 'disabled') {
          this.message = 'Utilisateur désactivé, Veuillez contacter votre Administrateur';
        }
      },
    });
  }
}