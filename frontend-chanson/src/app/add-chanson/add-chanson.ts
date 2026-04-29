import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Chanson } from '../model/chanson.model';
import { Genre } from '../model/genre.model';
import { ChansonService } from '../services/chanson-service';

@Component({
  selector: 'app-add-chanson',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-chanson.html',
})
export class AddChanson implements OnInit {

  newChanson = new Chanson();
  genres!: Genre[];
  newIdGen!: number;
  uploadedImage!: File;
  imagePath: any;

  constructor(private chansonService: ChansonService, private router: Router) {}

  ngOnInit() {
    this.chansonService.listeGenres().subscribe((gens: Genre[]) => {
      this.genres = gens;
    });
  }

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => { this.imagePath = reader.result; };
  }

  addChanson() {
    if (!this.newIdGen) return;
    this.newChanson.genre = this.genres.find(gen => gen.idGenre == this.newIdGen)!;
    this.chansonService.ajouterChanson(this.newChanson).subscribe((chanson: Chanson) => {
      if (this.uploadedImage) {
        this.chansonService.uploadImageChanson(
          this.uploadedImage, this.uploadedImage.name, chanson.idChanson!
        ).subscribe(() => {
          this.router.navigate(['chansons']);
        });
      } else {
        this.router.navigate(['chansons']);
      }
    });
  }
}