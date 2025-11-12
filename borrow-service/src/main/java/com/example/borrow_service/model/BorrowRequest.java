package com.example.borrow_service.model;


import java.time.LocalDate;

public class BorrowRequest {
    private int userId;
    private int librarianId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRequest() {
    }

    public BorrowRequest(int userId, int librarianId, int bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.userId = userId;
        this.librarianId = librarianId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "BorrowRequest{" +
                "userId=" + userId +
                ", librarianId=" + librarianId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
