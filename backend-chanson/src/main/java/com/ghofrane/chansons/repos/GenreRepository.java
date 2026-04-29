package com.ghofrane.chansons.repos;

import com.ghofrane.chansons.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(exported = false)
@CrossOrigin("http://localhost:4200/")
public interface GenreRepository extends JpaRepository<Genre, Long> {
}