import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({ providedIn: 'root' })
export class Auth {

  apiURL: string = 'http://localhost:8082';
  token!: string;
  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];
  public registredUser: User = new User();
  private helper = new JwtHelperService();

  constructor(private router: Router, private http: HttpClient) {}

  login(user: User) {
    return this.http.post<User>(this.apiURL + '/users/login', user, { observe: 'response' });
  }

  saveToken(jwt: string) {
    const cleanToken = jwt.startsWith('Bearer ') ? jwt.substring(7) : jwt;
    localStorage.setItem('jwt', cleanToken);
    this.token = cleanToken;
    this.isloggedIn = true;
    this.decodeJWT();
  }

  decodeJWT() {
    if (!this.token) return;
    try {
      const decodedToken = this.helper.decodeToken(this.token);
      this.roles = decodedToken.roles;
      this.loggedUser = decodedToken.sub;
    } catch (e) {
      console.error('Erreur décodage JWT', e);
    }
  }

  loadToken() {
    this.token = localStorage.getItem('jwt')!;
    if (this.token) {
      this.isloggedIn = true;
    }
    this.decodeJWT();
  }

  getToken(): string {
    if (!this.token) {
      this.token = localStorage.getItem('jwt')!;
    }
    return this.token;
  }

  isTokenExpired(): Boolean {
    return this.helper.isTokenExpired(this.token);
  }

  isAdmin(): Boolean {
    if (!this.roles) return false;
    return this.roles.indexOf('ADMIN') >= 0;
  }

  logout() {
    this.loggedUser = undefined!;
    this.roles = undefined!;
    this.token = undefined!;
    this.isloggedIn = false;
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }

  setRegistredUser(user: User) {
    this.registredUser = user;
  }

  getRegistredUser(): User {
    return this.registredUser;
  }

  registerUser(user: User) {
    return this.http.post<User>(this.apiURL + '/users/register', user, { observe: 'response' });
  }

  validateEmail(code: string) {
    return this.http.get<User>(this.apiURL + '/users/verifyEmail/' + code);
  }
}