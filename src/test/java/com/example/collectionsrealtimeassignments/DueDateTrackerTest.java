package com.example.collectionsrealtimeassignments;



import com.example.collectionsrealtimeassignments.libraryManagmentSystemService.DueDateTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DueDateTrackerTest {

    private DueDateTracker dueDateTracker;

    @BeforeEach
    public void setUp() {
        dueDateTracker = new DueDateTracker();
    }

    @Test
    public void testAddBorrowedBook() throws Exception {
        String bookName = "Book A";
        Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-25");
        dueDateTracker.addBorrowedBook(bookName, dueDate);

        DueDateTracker.BorrowedBook nearestDueDateBook = dueDateTracker.getNearestDueDate();

        assertNotNull(nearestDueDateBook, "The nearest due date book should not be null.");
        assertEquals(bookName, nearestDueDateBook.getBookName(), "The book name should match.");
        assertEquals(dueDate, nearestDueDateBook.getDueDate(), "The due date should match.");
    }

    @Test
    public void testGetNearestDueDate() throws Exception {
        Date dueDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-25");
        Date dueDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-20");

        dueDateTracker.addBorrowedBook("Book A", dueDate1);
        dueDateTracker.addBorrowedBook("Book B", dueDate2);

        DueDateTracker.BorrowedBook nearestDueDateBook = dueDateTracker.getNearestDueDate();

        assertNotNull(nearestDueDateBook, "The nearest due date book should not be null.");
        assertEquals("Book B", nearestDueDateBook.getBookName(), "The book with the nearest due date should be returned.");
        assertEquals(dueDate2, nearestDueDateBook.getDueDate(), "The due date should match the earliest date.");
    }

    @Test
    public void testNotifyOverdueBooks() throws Exception {
        Date pastDueDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-10");
        Date pastDueDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-15");
        Date futureDueDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-25");

        dueDateTracker.addBorrowedBook("Overdue Book 1", pastDueDate1);
        dueDateTracker.addBorrowedBook("Overdue Book 2", pastDueDate2);
        dueDateTracker.addBorrowedBook("Upcoming Book", futureDueDate);

        dueDateTracker.notifyOverdueBooks();

        DueDateTracker.BorrowedBook nearestDueDateBook = dueDateTracker.getNearestDueDate();

        assertNotNull(nearestDueDateBook, "The nearest due date book should not be null.");
        assertEquals("Upcoming Book", nearestDueDateBook.getBookName(), "Only the upcoming book should remain.");
        assertEquals(futureDueDate, nearestDueDateBook.getDueDate(), "The due date of the upcoming book should match.");
    }

    @Test
    public void testNotifyOverdueBooksWithNoOverdueBooks() throws Exception {
        Date futureDueDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-25");
        Date futureDueDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-30");

        dueDateTracker.addBorrowedBook("Book A", futureDueDate1);
        dueDateTracker.addBorrowedBook("Book B", futureDueDate2);

        dueDateTracker.notifyOverdueBooks();

        DueDateTracker.BorrowedBook nearestDueDateBook = dueDateTracker.getNearestDueDate();

        assertNotNull(nearestDueDateBook, "The nearest due date book should not be null.");
        assertEquals("Book A", nearestDueDateBook.getBookName(), "The nearest due date book should remain unchanged.");
        assertEquals(futureDueDate1, nearestDueDateBook.getDueDate(), "The due date should remain unchanged.");
    }

    @Test
    public void testGetNearestDueDateWithEmptyQueue() {
        DueDateTracker.BorrowedBook nearestDueDateBook = dueDateTracker.getNearestDueDate();

        assertNull(nearestDueDateBook, "The nearest due date book should be null for an empty queue.");
    }
}


