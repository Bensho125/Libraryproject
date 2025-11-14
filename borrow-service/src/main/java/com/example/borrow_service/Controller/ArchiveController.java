package com.example.borrow_service.Controller;


import com.example.borrow_service.model.Borrow;
import com.example.borrow_service.repo.ArchiveRepo;
import com.example.borrow_service.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("borrowArchive")
public class ArchiveController {

    @Autowired
    ArchiveService service;

    @GetMapping("getAll")
    public List<Borrow> getAll(){
        return service.getAll();

    }

    @GetMapping("getById/{id}")
    public Borrow getById(@PathVariable int id){
        return service.getById(id);
    }
}
