package com.ghofrane.chansons.service;

import java.util.List;
import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.entities.Genre;
import com.ghofrane.chansons.repos.ChansonRepository;
import com.ghofrane.chansons.repos.GenreRepository;
import com.ghofrane.chansons.repos.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ChansonServiceImpl implements ChansonService {

    @Autowired
    ChansonRepository chansonRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ImageRepository imageRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Chanson saveChanson(Chanson c) {
        if (c.getGenre() != null && c.getGenre().getIdGenre() != null) {
            Genre genre = genreRepository.findById(c.getGenre().getIdGenre())
                .orElse(null);
            c.setGenre(genre);
        }
        return chansonRepository.save(c);
    }

    @Override
    public Chanson updateChanson(Chanson c) {
        if (c.getGenre() != null && c.getGenre().getIdGenre() != null) {
            Genre genre = genreRepository.findById(c.getGenre().getIdGenre())
                .orElse(null);
            c.setGenre(genre);
        }
        return chansonRepository.save(c);
    }

    @Override
    public void deleteChanson(Chanson c) {
        chansonRepository.delete(c);
    }

    @Override
    @Transactional
    public void deleteChansonById(Long id) {
        imageRepository.deleteByChansonIdChanson(id);
        chansonRepository.deleteById(id);
    }

    @Override
    public Chanson getChanson(Long id) {
        return chansonRepository.findById(id).get();
    }

    @Override
    public List<Chanson> getAllChansons() {
        return chansonRepository.findAll();
    }

    @Override
    public List<Chanson> findByTitreChanson(String titre) {
        return chansonRepository.findByTitreChanson(titre);
    }

    @Override
    public List<Chanson> findByTitreChansonContains(String titre) {
        return chansonRepository.findByTitreChansonContains(titre);
    }

    @Override
    public List<Chanson> findByGenre(Genre genre) {
        return chansonRepository.findByGenre(genre);
    }

    @Override
    public List<Chanson> findByGenreIdGenre(Long id) {
        return chansonRepository.findByGenreIdGenre(id);
    }

    @Override
    public List<Chanson> findByOrderByTitreChansonAsc() {
        return chansonRepository.findByOrderByTitreChansonAsc();
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}