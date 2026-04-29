package com.ghofrane.chansons.repos;

import java.util.List;
import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ChansonRepository extends JpaRepository<Chanson, Long> {

    List<Chanson> findByTitreChanson(String titre);
    List<Chanson> findByTitreChansonContains(String titre);

    @Query("select c from Chanson c where c.genre = ?1")
    List<Chanson> findByGenre(Genre genre);

    List<Chanson> findByGenreIdGenre(Long id);
    List<Chanson> findByOrderByTitreChansonAsc();

    @Query("select c from Chanson c order by c.titreChanson asc, c.dateSortie asc")
    List<Chanson> trierChansonsTitreDate();
}