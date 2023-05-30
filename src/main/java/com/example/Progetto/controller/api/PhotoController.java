package com.example.Progetto.controller.api;

import com.example.Progetto.model.Photo;
import com.example.Progetto.service.DBPhotoService;
import com.example.Progetto.service.IPhotoService;
import com.example.Progetto.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@RestController
public class PhotoController {
    @Autowired
    @Qualifier("mainPhotoService")
    private IPhotoService photoService;

    public PhotoController() {
    }

    @RequestMapping("/api/photos")
    public Iterable<Photo> getAll() {

        return photoService.getAll();
    }

    @RequestMapping("/api/photos/{id}")
    public Photo getById(@PathVariable int id) {

        Optional<Photo> photo = photoService.getById(id);

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No photo found");
        }
        return photo.get();
    }
}


