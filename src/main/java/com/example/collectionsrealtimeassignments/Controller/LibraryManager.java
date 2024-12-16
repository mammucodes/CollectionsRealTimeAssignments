package com.example.collectionsrealtimeassignments.Controller;

import com.example.collectionsrealtimeassignments.libraryManagmentSystemService.BorrowingSystem;
import com.example.collectionsrealtimeassignments.libraryManagmentSystemService.DueDateTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
@RequestMapping("api/library")
public class LibraryManager {
    @Autowired
    private BorrowingSystem borrowingSystem ;
    @Autowired
    private DueDateTracker dueDateTracker ;

@GetMapping("/borrowBook")
    public void borrowBook(String user, String book, Date dueDate) {
        borrowingSystem.borrowBook(user, book);
        dueDateTracker.addBorrowedBook(book, dueDate);
    }
@GetMapping("returnBook")
    public boolean returnBook(String user, String book) {
        boolean isReturned = borrowingSystem.returnBook(user, book);
        if (isReturned) {
            // Optionally remove from dueDateTracker if implemented
        }
        return isReturned;
    }
    @GetMapping("/nearbyDueDate")
    public DueDateTracker.BorrowedBook getNextDueBook() {
        return dueDateTracker.getNearestDueDate();
    }
}

