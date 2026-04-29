import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Genre } from '../model/genre.model';
import { ChansonService } from '../services/chanson-service';

@Component({
  selector: 'app-update-genre',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './update-genre.html',
  styles: ``
})
export class UpdateGenre implements OnInit {

  @Input() genre!: Genre;
  @Input() ajout!: boolean;
  @Output() genreUpdated = new EventEmitter<Genre>();

  constructor(private chansonService: ChansonService) {}

  ngOnInit(): void {}

  saveGenre() {
    if (this.ajout) {
      const newGenre: Partial<Genre> = {
        nomGenre: this.genre.nomGenre,
        descriptionGenre: this.genre.descriptionGenre
      };
      this.chansonService.ajouterGenre(newGenre).subscribe(
        gen => {
          this.genreUpdated.emit(gen);
          this.genre.nomGenre = '';
          this.genre.descriptionGenre = '';
        },
        err => console.error('Erreur ajout genre', err)
      );
    } else {
      this.chansonService.updateGenre(this.genre).subscribe(
        gen => this.genreUpdated.emit(gen),
        err => console.error('Erreur mise à jour genre', err)
      );
    }
  }
}