package com.example.borrow_service.repo;


import com.example.borrow_service.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepo extends JpaRepository<Borrow, Integer> {

    @Query("SELECT b FROM Borrow b WHERE b.bookId = :bookId")
    List<Borrow> findAllByBookId(@Param("bookId") int bookId);

    @Query("SELECT b FROM Borrow b WHERE b.bookTitle = :bookName AND b.userName = :username")
    Borrow findByBookNameAndUserName(@Param("bookName") String bookName,
                                     @Param("username") String username);

}
