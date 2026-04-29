package com.ghofrane.chansons.service;

import com.ghofrane.chansons.entities.Chanson;
import com.ghofrane.chansons.entities.Image;
import com.ghofrane.chansons.repos.ChansonRepository;
import com.ghofrane.chansons.repos.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ChansonRepository chansonRepository;

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        return imageRepository.save(
            Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .build()
        );
    }

    @Override
    public Image getImageDetails(Long id) throws IOException {
        Optional<Image> dbImage = imageRepository.findById(id);
        return Image.builder()
            .idImage(dbImage.get().getIdImage())
            .name(dbImage.get().getName())
            .type(dbImage.get().getType())
            .image(dbImage.get().getImage())
            .build();
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(dbImage.get().getType()))
            .body(dbImage.get().getImage());
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Image uploadImageChanson(MultipartFile file, Long idChanson) throws IOException {
        Chanson c = new Chanson();
        c.setIdChanson(idChanson);
        return imageRepository.save(
            Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .chanson(c)
                .build()
        );
    }

    @Override
    public List<Image> getImagesParChanson(Long chansonId) {
        Chanson c = chansonRepository.findById(chansonId).get();
        return c.getImages();
    }
}