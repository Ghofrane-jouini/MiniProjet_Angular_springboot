import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Chanson } from '../model/chanson.model';
import { ChansonService } from '../services/chanson-service';
import { SearchFilterPipe } from '../search-filter-pipe';

@Component({
  selector: 'app-recherche-par-titre',
  standalone: true,
  imports: [FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './recherche-par-titre.html',
  styles: ``,
})
export class RechercheParTitre implements OnInit {

  titreChanson!: string;
  chansons!: Chanson[];
  searchTerm!: string;

  constructor(private chansonService: ChansonService) {}

  ngOnInit(): void {
    this.chansonService.listeChansons().subscribe((chans: Chanson[]) => {
      console.log(chans);
      this.chansons = chans;
    });
  }

  rechercherChansons() {
    if (this.titreChanson) {
      this.chansonService.rechercherParNom(this.titreChanson).subscribe((chans: Chanson[]) => {
        this.chansons = chans;
        console.log(chans);
      });
    } else {
      this.chansonService.listeChansons().subscribe((chans: Chanson[]) => {
        console.log(chans);
        this.chansons = chans;
      });
    }
  }
}