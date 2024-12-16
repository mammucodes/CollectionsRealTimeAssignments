package com.example.collectionsrealtimeassignments.libraryManagmentSystemService;

import com.example.collectionsrealtimeassignments.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BorrowingSystem {
    private Map<String, Set<String>> borrowedbooks;
    private static final Logger logger = LoggerFactory.getLogger(BorrowingSystem.class);

    public BorrowingSystem() {
        borrowedbooks = new HashMap<>();
    }

    public void addBorrwedBooks(String user, Set<String> bookslist) {
        borrowedbooks.put(user, bookslist);
    }

    // this method user can borrow books if he already not took the same book
    //it returns false if book is already borrowed and no available.
    //it returns true if book is available
    public Boolean borrowBook(String user, String book) {
        logger.info("borrowbook method called");
        borrowedbooks.computeIfAbsent(user, u -> new HashSet<>());
        boolean bookisborrowed = isBookBorrowed(book);
        if (bookisborrowed) {
            logger.info(" given book is already borrowed no available books");
            return false;
        } else
            logger.info("book is aviable you can borrow it ");
        borrowedbooks.get(user).add(book);
        return true;
    }
//this method returns borrwed book from library
    // if book returned it returns true . if not found ut returns false

    public boolean returnBook(String user, String book) {
        logger.debug("checks if any books to return ");
        if (borrowedbooks.containsKey(user) && borrowedbooks.get(user).contains(book)) {
            logger.debug("found the book in borrowed book list");
            borrowedbooks.get(user).remove(book);
            logger.debug("successfully removed borrwed book when he returns");
            return true;
        }
        logger.debug("no  such book present in borrowed book list so, cannot return");
        return false;
    }

    // this method checks if any book borrwed
    //if borrowed it returns true or esle returns false
    public boolean isBookBorrowed(String book) {
        logger.debug("isBorrowed book is called");
        boolean bookIsBorrowed = false;
        for (Set<String> books : borrowedbooks.values()) {
            if (books.contains(book)) {
                logger.info("borrowed book contain  so , it breaks");
                bookIsBorrowed = true;
                break;
            }
        }
        if (bookIsBorrowed) {
            logger.info("found borrowed book returns true");
            return true;
        } else
            logger.info("no  borrowed book found  returns false");
        return false;

        // return borrowedbooks.values().stream()
        // .anyMatch(books -> books.contains(book));

    }

    public Set<String> getBorrowedBooks(String user) {
        logger.debug("returns list of borrwed books");
        return borrowedbooks.getOrDefault(user, Collections.emptySet());
    }


}
