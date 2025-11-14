package com.example.borrow_service.repo;

import com.example.borrow_service.model.Borrow;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
@Table(name = "borrow_archive")
public interface ArchiveRepo extends JpaRepository<Borrow, Integer> {

}
