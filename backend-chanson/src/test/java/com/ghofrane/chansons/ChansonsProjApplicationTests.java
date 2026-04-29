package com.ghofrane.chansons;

import java.util.Date;
import java.util.List;

import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.entities.Genre;
import com.ghofrane.chansons.repos.ChansonRepository;
import com.ghofrane.chansons.repos.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ChansonsProjApplicationTests {

    @Autowired
    private ChansonRepository chansonRepository;

    @Autowired
    private GenreRepository genreRepository;

    private Genre testGenre;

    @BeforeEach
    void setup() {
        testGenre = new Genre();
        testGenre.setNomGenre("Pop");
        testGenre = genreRepository.save(testGenre);
    }

    private Chanson createSampleChanson(String titre) {
        Chanson chanson = new Chanson(
            titre,
            "Artiste Test",
            3.5,
            new Date()
        );
        chanson.setGenre(testGenre);
        return chansonRepository.save(chanson);
    }

    @Test
    public void testCreateChanson() {
        Chanson c = new Chanson("Titre Test", "Artiste Test", 3.5, new Date());
        c.setGenre(testGenre);
        chansonRepository.save(c);
    }

    @Test
    public void testFindChanson() {
        Chanson created = createSampleChanson("Titre Find");
        Chanson c = chansonRepository.findById(created.getIdChanson()).get();
        System.out.println(c);
    }

    @Test
    public void testUpdateChanson() {
        Chanson created = createSampleChanson("Titre Initial");
        Chanson c = chansonRepository.findById(created.getIdChanson()).get();
        c.setTitreChanson("Titre Modifié");
        chansonRepository.save(c);
        System.out.println(c);
    }

    @Test
    public void testDeleteChanson() {
        Chanson created = createSampleChanson("Titre Delete");
        chansonRepository.deleteById(created.getIdChanson());
    }

    @Test
    public void testListerToutesChansons() {
        List<Chanson> chansons = chansonRepository.findAll();
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testFindByTitreChanson() {
        createSampleChanson("Titre Java");
        List<Chanson> chansons = chansonRepository.findByTitreChanson("Titre Java");
        System.out.println("RECHERCHE CHANSON PAR TITRE: ");
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testFindByTitreChansonContains() {
        createSampleChanson("Titre Web");
        List<Chanson> chansons = chansonRepository.findByTitreChansonContains("Titre");
        System.out.println("CHANSONS CONTENANT 'Titre': ");
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testFindByGenre() {
        createSampleChanson("Titre Genre");
        List<Chanson> chansons = chansonRepository.findByGenre(testGenre);
        System.out.println("CHANSONS PAR GENRE: ");
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testFindByGenreId() {
        createSampleChanson("Titre Genre ID");
        List<Chanson> chansons = chansonRepository.findByGenreIdGenre(testGenre.getIdGenre());
        System.out.println("RECHERCHE PAR ID GENRE: ");
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testFindByOrderByTitreChansonAsc() {
        List<Chanson> chansons = chansonRepository.findByOrderByTitreChansonAsc();
        System.out.println("ORDRE CROISSANT DES TITRES: ");
        for (Chanson c : chansons)
            System.out.println(c);
    }

    @Test
    public void testTrierChansonsTitreDate() {
        List<Chanson> chansons = chansonRepository.trierChansonsTitreDate();
        System.out.println("ORDRE CROISSANT TITRES / CROISSANT DATE: ");
        for (Chanson c : chansons)
            System.out.println(c);
    }
}