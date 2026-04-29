package com.ghofrane.chansons.service;

import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.entities.Genre;
import java.util.List;

public interface ChansonService {

    Chanson saveChanson(Chanson c);
    Chanson updateChanson(Chanson c);
    void deleteChanson(Chanson c);
    void deleteChansonById(Long id);
    Chanson getChanson(Long id);
    List<Chanson> getAllChansons();
    List<Chanson> findByTitreChanson(String titre);
    List<Chanson> findByTitreChansonContains(String titre);
    List<Chanson> findByGenre(Genre genre);
    List<Chanson> findByGenreIdGenre(Long id);
    List<Chanson> findByOrderByTitreChansonAsc();
    List<Genre> getAllGenres();
}