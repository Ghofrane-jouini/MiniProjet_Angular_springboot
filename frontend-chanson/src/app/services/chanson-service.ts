import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auth } from './auth';
import { Chanson } from '../model/chanson.model';
import { Genre } from '../model/genre.model';
import { Image } from '../model/image.model';

@Injectable({ providedIn: 'root' })
export class ChansonService {

  apiURL = 'http://localhost:8083/chansons/api';
  apiURLGenre = 'http://localhost:8083/chansons/genres';

  constructor(private http: HttpClient, private authService: Auth) {}

  listeChansons(): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(this.apiURL + '/all');
  }

  ajouterChanson(chanson: Chanson): Observable<Chanson> {
    return this.http.post<Chanson>(`${this.apiURL}/addchanson`, chanson);
  }

  consulterChanson(id: number): Observable<Chanson> {
    return this.http.get<Chanson>(`${this.apiURL}/getbyid/${id}`);
  }

  updateChanson(chanson: Chanson): Observable<Chanson> {
    return this.http.put<Chanson>(`${this.apiURL}/updatechanson`, chanson);
  }

  supprimerChanson(id: number) {
    return this.http.delete(`${this.apiURL}/delchanson/${id}`);
  }

  listeGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(this.apiURLGenre);
  }

  ajouterGenre(gen: Partial<Genre>): Observable<Genre> {
    return this.http.post<Genre>(this.apiURLGenre, gen);
  }

  updateGenre(genre: Genre): Observable<Genre> {
    return this.http.put<Genre>(`${this.apiURLGenre}/${genre.idGenre}`, genre);
  }

  rechercherParNom(nom: string): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(`${this.apiURL}/chansonByName/${nom}`);
  }

  rechercherParGenre(idGen: number): Observable<Chanson[]> {
    return this.http.get<Chanson[]>(`${this.apiURL}/chansongen/${idGen}`);
  }

  private get imageURL(): string {
    return this.apiURL.replace('/api', '');
  }

  uploadImage(file: File, filename: string): Observable<Image> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    return this.http.post<Image>(this.imageURL + '/image/upload', imageFormData);
  }

  loadImage(id: number): Observable<Image> {
    return this.http.get<Image>(this.imageURL + '/image/get/info/' + id);
  }

  uploadImageChanson(file: File, filename: string, idChanson: number): Observable<any> {
    const imageFormData = new FormData();
    imageFormData.append('image', file, filename);
    return this.http.post(this.imageURL + '/image/uploadImageChanson/' + idChanson, imageFormData);
  }

  supprimerImage(id: number): Observable<any> {
    return this.http.delete(this.imageURL + '/image/delete/' + id);
  }
}