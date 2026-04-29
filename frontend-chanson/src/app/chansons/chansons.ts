import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Auth } from '../services/auth';
import { ChansonService } from '../services/chanson-service';
import { Chanson } from '../model/chanson.model';

@Component({
  selector: 'app-chansons',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './chansons.html',
  
})
export class Chansons implements OnInit {

  chansons!: Chanson[];

  constructor(private chansonService: ChansonService, public authService: Auth) {}

  ngOnInit() {
    this.chargerChansons();
  }

  chargerChansons() {
    this.chansonService.listeChansons().subscribe((chansons: Chanson[]) => {
      this.chansons = chansons;
      this.chansons.forEach(chanson => {
        if (chanson.images && chanson.images.length > 0) {
          chanson.imageStr = 'data:' + chanson.images[0].type + ';base64,' + chanson.images[0].image;
        }
      });
    });
  }

  supprimerChanson(chanson: Chanson) {
    if (confirm("Etes-vous sûr ?")) {
      this.chansonService.supprimerChanson(chanson.idChanson).subscribe(() => {
        this.chargerChansons();
      });
    }
  }
}