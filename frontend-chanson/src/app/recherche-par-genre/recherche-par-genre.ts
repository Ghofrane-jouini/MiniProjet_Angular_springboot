import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../services/auth';
import { Chanson } from '../model/chanson.model';
import { Genre } from '../model/genre.model';
import { ChansonService } from '../services/chanson-service';

@Component({
  selector: 'app-recherche-par-genre',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './recherche-par-genre.html',
  styles: ``
})
export class RechercheParGenre implements OnInit {

  chansons!: Chanson[];
  genres!: Genre[];
  idGen!: string;

  constructor(
    private chansonService: ChansonService,
    private router: Router,
    public authService: Auth
  ) {}

  ngOnInit(): void {
    this.chansons = [];
    this.chansonService.listeGenres().subscribe((gens: Genre[]) => {
      this.genres = gens;
    });
  }

  onChange() {
    if (this.idGen) {
      this.chansonService.rechercherParGenre(+this.idGen).subscribe((chans: Chanson[]) => {
        this.chansons = chans;
      });
    } else {
      this.chansons = [];
    }
  }

  supprimerChanson(chanson: Chanson) {
    if (confirm("Voulez-vous vraiment supprimer cette chanson ?")) {
      this.chansonService.supprimerChanson(chanson.idChanson!).subscribe(() => {
        this.chansons = this.chansons.filter(c => c.idChanson !== chanson.idChanson);
      });
    }
  }

  modifierChanson(chanson: Chanson) {
    this.router.navigate(['updateChanson', chanson.idChanson]);
  }
}