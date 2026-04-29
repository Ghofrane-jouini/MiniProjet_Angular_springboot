import { Genre } from "./genre.model";
import { Image } from "./image.model";

export class Chanson {
    idChanson!: number;
    titreChanson?: string;
    artiste?: string;
    dureeChanson?: number;
    dateSortie?: Date;
    genre?: Genre;
    email!: string;
    images!: Image[];
    imageStr!: string;
}