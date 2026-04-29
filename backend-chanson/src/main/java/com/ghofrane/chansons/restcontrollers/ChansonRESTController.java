package com.ghofrane.chansons.restcontrollers;

import java.util.List;
import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.service.ChansonService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@CrossOrigin
@AllArgsConstructor
public class ChansonRESTController {

    private final ChansonService chansonService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Chanson> getAllChansons() {
        return chansonService.getAllChansons();
    }

    @RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
    public Chanson getChansonById(@PathVariable("id") Long id) {
        return chansonService.getChanson(id);
    }

    @RequestMapping(path = "/addchanson", method = RequestMethod.POST)
    public Chanson createChanson(@RequestBody Chanson chanson) {
        return chansonService.saveChanson(chanson);
    }

    @RequestMapping(path = "/updatechanson", method = RequestMethod.PUT)
    public Chanson updateChanson(@RequestBody Chanson chanson) {
        return chansonService.updateChanson(chanson);
    }

    @RequestMapping(value = "/delchanson/{id}", method = RequestMethod.DELETE)
    public void deleteChanson(@PathVariable("id") Long id) {
        chansonService.deleteChansonById(id);
    }

    @RequestMapping(value = "/chansonsByTitre/{titre}", method = RequestMethod.GET)
    public List<Chanson> findByTitreChansonContains(@PathVariable("titre") String titre) {
        return chansonService.findByTitreChansonContains(titre);
    }

    @RequestMapping(value = "/chansonsgen/{idGenre}", method = RequestMethod.GET)
    public List<Chanson> getChansonsByGenreId(@PathVariable("idGenre") Long idGenre) {
        return chansonService.findByGenreIdGenre(idGenre);
    }
}