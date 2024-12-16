package com.example.collectionsrealtimeassignments.libraryManagmentSystemService;

import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class DueDateTracker {
    private PriorityQueue<BorrowedBook> dueDateQueue = new PriorityQueue<>(Comparator.comparing(BorrowedBook::getDueDate));//used method reference isntead of lambda

    public void addBorrowedBook(String book, Date dueDate) {
        dueDateQueue.add(new BorrowedBook(book, dueDate));
    }


    public BorrowedBook getNearestDueDate() {
        return dueDateQueue.peek(); // Peek at the head of the queue
    }

    public void notifyOverdueBooks() {
        Date today = new Date();
        while (!dueDateQueue.isEmpty() && dueDateQueue.peek().getDueDate().before(today)) {
            BorrowedBook overdueBook = dueDateQueue.poll();
            System.out.println("Book overdue: " + overdueBook.getBookName() + " (Due: " + overdueBook.getDueDate() + ")");
        }
    }

    // Internal class to represent a borrowed book
   public  static class BorrowedBook {
        private String bookName;
        private Date dueDate;

        public BorrowedBook(String bookName, Date dueDate) {
            this.bookName = bookName;
            this.dueDate = dueDate;
        }

        public String getBookName() {
            return bookName;
        }

        public Date getDueDate() {
            return dueDate;
        }
    }
}

