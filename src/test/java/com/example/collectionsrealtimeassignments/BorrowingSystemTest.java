package com.example.collectionsrealtimeassignments;

import com.example.collectionsrealtimeassignments.libraryManagmentSystemService.BorrowingSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Assertions.assertFalse(actual, "it should return false. because book is already borrowed no available books with given book name");


    }
}
