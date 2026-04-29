import { Routes } from '@angular/router';
import { Chansons } from './chansons/chansons';
import { AddChanson } from './add-chanson/add-chanson';
import { UpdateChanson } from './update-chanson/update-chanson';
import { RechercheParGenre } from './recherche-par-genre/recherche-par-genre';
import { RechercheParTitre } from './recherche-par-titre/recherche-par-titre';
import { ListeGenres } from './liste-genres/liste-genres';
import { UpdateGenre } from './update-genre/update-genre';
import { Login } from './login/login';
import { Forbidden } from './forbidden/forbidden';
import { chansonGuard } from './chanson-guard';
import { Register } from './register/register';
import { VerifEmail } from './verif-email/verif-email';
export const routes: Routes = [
    { path: "chansons", component: Chansons },
    { path: "add-chanson", component: AddChanson, canActivate: [chansonGuard] },
    { path: "", redirectTo: "chansons", pathMatch: "full" },
    { path: "updateChanson/:id", component: UpdateChanson, canActivate: [chansonGuard] },
    { path: "rechercheParGenre", component: RechercheParGenre },
    { path: "rechercheParTitre", component: RechercheParTitre },
    { path: "listeGenres", component: ListeGenres, canActivate: [chansonGuard] },
    { path: "updateGenre", component: UpdateGenre, canActivate: [chansonGuard] },
    { path: 'login', component: Login },
    { path: 'app-forbidden', component: Forbidden },
    { path: 'register', component: Register },
    { path: 'verifEmail', component: VerifEmail },
];