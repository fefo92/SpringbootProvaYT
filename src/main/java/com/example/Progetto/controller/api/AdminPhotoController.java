package com.example.Progetto.controller.api;

import com.example.Progetto.model.Photo;
import com.example.Progetto.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AdminPhotoController {
    private PhotoService photoService;

    public AdminPhotoController() {
        photoService = new PhotoService();

    }
    @RequestMapping("admin/api/photos")
    public Iterable<Photo> getAll(){

        return photoService.getAll();
    }
    @RequestMapping("admin/api/photos/{id}")
    public Photo getById(int id){

        Optional<Photo> photo = photoService.getById(id);

        if (photo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No photo found");
        }
        return photo.get();
    }

    @RequestMapping(value = "admin/api/photos", method = RequestMethod.POST)
    public Photo create(@RequestBody Photo photo){

        return photoService.create(photo);

    }

    @RequestMapping(value = "admin/api/photos/{id}", method = RequestMethod.PUT)
    public Photo update(@PathVariable int id, @RequestBody Photo photo){
        Optional<Photo> updatedPhoto = photoService.update(id, photo);

        if (updatedPhoto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No photo found");
        }
        return updatedPhoto.get();

    }
    @RequestMapping(value = "admin/api/photos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        Boolean isDeleted = photoService.delete(id);

        if (isDeleted == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No photo found");
        }
    }
}
