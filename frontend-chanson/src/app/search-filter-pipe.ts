import { Pipe, PipeTransform } from '@angular/core';
import { Chanson } from './model/chanson.model';

@Pipe({
  name: 'searchFilter',
  standalone: true,
})
export class SearchFilterPipe implements PipeTransform {
  transform(chansons: Chanson[], searchTerm: string): Chanson[] {
    if (!chansons || !searchTerm) return chansons;
    return chansons.filter((c) =>
      c.titreChanson?.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
}
