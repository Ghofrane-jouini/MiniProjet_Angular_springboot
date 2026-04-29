import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chanson } from '../model/chanson.model';
import { Genre } from '../model/genre.model';
import { Image } from '../model/image.model';
import { ChansonService } from '../services/chanson-service';

@Component({
  selector: 'app-update-chanson',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-chanson.html',
})
export class UpdateChanson implements OnInit {

  currentChanson = new Chanson();
  genres!: Genre[];
  updatedGenreId!: number;
  uploadedImage!: File;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private chansonService: ChansonService
  ) {}

  ngOnInit(): void {
    this.chansonService.listeGenres().subscribe(gens => {
      this.genres = gens;
    });
    this.chansonService.consulterChanson(
      this.activatedRoute.snapshot.params['id']
    ).subscribe(chanson => {
      this.currentChanson = chanson;
      this.updatedGenreId = this.currentChanson.genre?.idGenre!;
    });
  }

  onImageUpload(event: any) {
    if (event.target.files && event.target.files.length) {
      this.uploadedImage = event.target.files[0];
    }
  }

  onAddImageChanson() {
    this.chansonService.uploadImageChanson(
      this.uploadedImage, this.uploadedImage.name, this.currentChanson.idChanson
    ).subscribe((img: Image) => {
      this.currentChanson.images.push(img);
    });
  }

  supprimerImage(img: Image) {
    if (confirm("Etes-vous sûr ?")) {
      this.chansonService.supprimerImage(img.idImage).subscribe(() => {
        const index = this.currentChanson.images.indexOf(img, 0);
        if (index > -1) {
          this.currentChanson.images.splice(index, 1);
        }
      });
    }
  }

  updateChanson() {
    this.currentChanson.genre = this.genres.find(
      gen => gen.idGenre == this.updatedGenreId
    )!;
    this.chansonService.updateChanson(this.currentChanson).subscribe(() => {
      this.router.navigate(['chansons']);
    });
  }
}