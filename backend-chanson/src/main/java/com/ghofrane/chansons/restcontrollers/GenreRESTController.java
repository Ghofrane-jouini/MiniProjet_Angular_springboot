package com.ghofrane.chansons.restcontrollers;

import java.util.List;
import com.ghofrane.chansons.entities.Genre;
import com.ghofrane.chansons.repos.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@CrossOrigin(origins = "http://localhost:4200")
public class GenreRESTController {

    @Autowired
    GenreRepository genreRepository;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable("id") Long id) {
        return genreRepository.findById(id).get();
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable("id") Long id, @RequestBody Genre genre) {
        genre.setIdGenre(id);
        return genreRepository.save(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") Long id) {
        genreRepository.deleteById(id);
    }
}