package com.ghofrane.chansons.restcontrollers;

import com.ghofrane.chansons.entities.Image;
import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.service.ChansonService;
import com.ghofrane.chansons.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageRestController {

    @Autowired
    ImageService imageService;

    @Autowired
    ChansonService chansonService;

    @PostMapping("/upload")
    public Image uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }

    @GetMapping("/get/info/{id}")
    public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
        return imageService.getImageDetails(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
        return imageService.getImage(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }

    @PostMapping("/uploadFS/{id}")
    public void uploadImageFS(@RequestParam("image") MultipartFile file,
                              @PathVariable("id") Long id) throws IOException {
        Chanson c = chansonService.getChanson(id);
        c.setImagePath(id + ".jpg");
        Files.write(
            Paths.get(System.getProperty("user.home") + "/images/" + c.getImagePath()),
            file.getBytes()
        );
        chansonService.updateChanson(c);
    }

    @GetMapping(value = "/loadfromFS/{id}",
            produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {
        Chanson c = chansonService.getChanson(id);
        return Files.readAllBytes(
            Paths.get(System.getProperty("user.home") + "/images/" + c.getImagePath())
        );
    }

    @PostMapping("/uploadImageChanson/{idChanson}")
    public Image uploadImageChanson(@RequestParam("image") MultipartFile file,
                                    @PathVariable("idChanson") Long idChanson) throws IOException {
        return imageService.uploadImageChanson(file, idChanson);
    }

    @GetMapping("/getImagesChanson/{idChanson}")
    public List<Image> getImagesChanson(@PathVariable("idChanson") Long idChanson) throws IOException {
        return imageService.getImagesParChanson(idChanson);
    }
}