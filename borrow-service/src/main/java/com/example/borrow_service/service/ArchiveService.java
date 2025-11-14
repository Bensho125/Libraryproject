package com.example.borrow_service.service;


import com.example.borrow_service.model.Borrow;
import com.example.borrow_service.repo.ArchiveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveService {


    @Autowired
    ArchiveRepo repo;

    public List<Borrow> getAll() {

        return repo.findAll();
    }

    public Borrow getById(int id) {

        return repo.findById(id).get();
    }
}
