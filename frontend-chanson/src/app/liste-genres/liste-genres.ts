import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Genre } from '../model/genre.model';
import { ChansonService } from '../services/chanson-service';
import { UpdateGenre } from '../update-genre/update-genre';

@Component({
  selector: 'app-liste-genres',
  standalone: true,
  imports: [CommonModule, UpdateGenre],
  templateUrl: './liste-genres.html'
})
export class ListeGenres implements OnInit {

  genres!: Genre[];
  updatedGen: Genre = { idGenre: 0, nomGenre: '', descriptionGenre: '' };
  ajout: boolean = true;

  constructor(private chansonService: ChansonService) {}

  ngOnInit(): void {
    this.chargerGenres();
  }

  chargerGenres() {
    this.chansonService.listeGenres().subscribe(genres => {
      this.genres = genres;
    });
  }

  genreUpdated(gen: Genre) {
    if (this.ajout) {
      this.chansonService.ajouterGenre(gen).subscribe(() => this.chargerGenres());
    } else {
      this.chansonService.updateGenre(gen).subscribe(() => this.chargerGenres());
    }
    this.chargerGenres();
    this.updatedGen = { idGenre: 0, nomGenre: '', descriptionGenre: '' };
    this.ajout = true;
  }

  updateGen(gen: Genre) {
    this.updatedGen = { ...gen };
    this.ajout = false;
  }
}