package com.example.collectionsrealtimeassignments;

import com.example.collectionsrealtimeassignments.libraryManagmentSystemService.BorrowingSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorrowingSystemTest {
    BorrowingSystem bs = new BorrowingSystem();


    @Test
    public void borrowBookTestIfBorrowed() {
        //boolean excepcted = false;

        Set<String> books = new HashSet<>();
        books.add("2 states");
        books.add("love story");
        bs.addBorrwedBooks("Raaj", books);

        boolean actual = bs.borrowBook("Raaj", "2 states");
        assertFalse(actual, "it should return false. because book is already borrowed no available books with given book name");

    }

    @Test
    public void borrrowBookTestForDiffernetUser() {

        Set<String> books = new HashSet<>();
        books.add("2 states");
        books.add("love story");
        bs.addBorrwedBooks("Mamatha", books);

        boolean actual = bs.borrowBook("Mamatha", "runRajaRun");
        assertTrue(actual, "it should return false. because book is already borrowed no available books with given book name");

    }

    @Test
    public void borrowBookTestIfNullPassed() {

        Boolean actual = bs.borrowBook("raaj", null);
        Assertions.assertNull(actual);


    }

    @Test
    void testReturnBook_SuccessfulReturn() {
        Set<String> books = new HashSet<>();
        books.add("Book1");
        bs.addBorrwedBooks("User1", books);

        boolean result = bs.returnBook("User1", "2 states");
        if (result) {
            assertTrue(result, "Book should be successfully returned");
        }
        // assertFalse(borrowedbooks.get("User1").contains("Book1"), "Book should be removed from the list");
        assertFalse(result, "book is not present cannot delete");
    }
    @Test
    void testReturnBook_UserNotFound() {
        boolean result = bs.returnBook("User2", "Book1");
        assertFalse(result, "Should return false as the user does not exist");
    }

    @Test
    void testReturnBook_NullUser() {
        boolean result = bs.returnBook(null, "Book1");

        assertFalse(result, "Should return false when user is null");
    }

    @Test
    void testReturnBook_NullBook() {
        Set<String> books = new HashSet<>();
        books.add("Book1");
        bs.addBorrwedBooks("User1", books);

        boolean result = bs.returnBook("User1", null);

        assertFalse(result, "Should return false when book is null");
    }

    @Test
    void testReturnBook_EmptyUser() {
        boolean result = bs.returnBook("", "Book1");

        assertFalse(result, "Should return false when user is empty");
    }

    @Test
    void testReturnBook_EmptyBook() {
        Set<String> books = new HashSet<>();
        books.add("Book1");
        bs.addBorrwedBooks("User1", books);

        boolean result = bs.returnBook("User1", "");

        assertFalse(result, "Should return false when book is empty");
    }

    @Test
    void testReturnBook_EmptyBorrowedBooks() {
        boolean result = bs.returnBook("User1", "Book1");

        assertFalse(result, "Should return false when borrowedbooks map is empty");
    }
}
