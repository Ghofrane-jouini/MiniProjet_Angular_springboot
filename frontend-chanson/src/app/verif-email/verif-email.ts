import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Auth } from '../services/auth';
import { User } from '../model/user.model';

@Component({
  selector: 'app-verif-email',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './verif-email.html',
})
export class VerifEmail implements OnInit {

  code: string = '';
  user: User = new User();
  err = '';

  constructor(private authService: Auth, private router: Router) {}

  ngOnInit(): void {
    this.user = this.authService.getRegistredUser();
  }

  onValidateEmail() {
    this.authService.validateEmail(this.code).subscribe({
      next: (res) => {
        alert('Email validé ! Connexion en cours...');
        this.authService.login(this.user).subscribe({
          next: (data) => {
            let jwToken = data.headers.get('Authorization')!;
            this.authService.saveToken(jwToken);
            this.router.navigate(['/chansons']);
          },
          error: (err: any) => {
            console.log(err);
            this.router.navigate(['/login']);
          },
        });
      },
      error: (err: any) => {
        if (err.error?.errorCode === 'INVALID_TOKEN') {
          this.err = 'Code invalide !';
        } else if (err.error?.errorCode === 'EXPIRED_TOKEN') {
          this.err = 'Code a expiré !';
        } else {
          this.err = 'Erreur de validation.';
        }
      },
    });
  }
}