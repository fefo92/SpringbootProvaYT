package com.example.Progetto.service;

import com.example.Progetto.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotoService {
    private List<Photo> list;
    private int lastId;

    public PhotoService() {
        list = new ArrayList<>();
        list.add(new Photo(1, "./img/01.png"));
        list.add(new Photo(2, "./img/02.png"));
        list.add(new Photo(3, "./img/03.png"));

        lastId = 3;

    }
    public Iterable<Photo> getAll(){

        return list ;
    }
    public Optional<Photo> getById(int id){

        Optional<Photo> photo = list.stream().filter(item -> item.getId() == id).findFirst();

        return photo;
    }
    public Photo create(Photo photo){
        lastId++;

        photo.setId(lastId);

        list.add(photo);

        return photo;

    }
    public Optional<Photo> update(int id, Photo photo){
        Optional<Photo> foundPhoto = list.stream().filter(item -> item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
           return Optional.empty() ;
        }
        foundPhoto.get().setUrl(photo.getUrl());
        return foundPhoto;

    }
    public Boolean delete(int id){
        Optional<Photo> foundPhoto = list.stream().filter(item -> item.getId() == id).findFirst();

        if (foundPhoto.isEmpty()) {
            return false;
        }
        list.remove(foundPhoto.get());
        return true;

    }
}