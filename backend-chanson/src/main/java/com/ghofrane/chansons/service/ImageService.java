package com.ghofrane.chansons.service;

import com.ghofrane.chansons.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;
    Image getImageDetails(Long id) throws IOException;
    ResponseEntity<byte[]> getImage(Long id) throws IOException;
    void deleteImage(Long id);
    Image uploadImageChanson(MultipartFile file, Long idChanson) throws IOException;
    List<Image> getImagesParChanson(Long chansonId);
}