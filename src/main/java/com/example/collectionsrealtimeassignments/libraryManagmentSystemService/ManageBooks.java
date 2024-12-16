package com.example.collectionsrealtimeassignments.libraryManagmentSystemService;

import com.example.collectionsrealtimeassignments.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ManageBooks {
    // private String author;
    Map<String, Book> booksList;

    public ManageBooks() {
        booksList = new HashMap<>();
    }

    private static final Logger logger = LoggerFactory.getLogger(ManageBooks.class);

    public void addBookDetails(Book book) {
        if (book.getTitle() != null && !book.getTitle().trim().isEmpty()) {
            booksList.put(book.getTitle(), book);
            logger.debug("book added succesfully to the managebook list");
        }
        logger.debug("failed to add book to list since book  title is null or empty");
    }

    // for my  library only one book with same title exist i.e., title of book is unique
    //this method takes title as input
    //if given book title is null or empty we returns null;
    //  and if book found it returns book object with all book details
    //if  book with given title not found it returns null;
    public Book getBookDetailsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }

        if (booksList.containsKey(title)) {
            Book titleBookFound = booksList.get(title);
            logger.debug("book with  title " + title + "found ");
            return titleBookFound;
        }
        logger.debug("no book with such title " + title + " exist");
        return null;
    }
//This method takes input  author and
    //returns list of book with same author name
    //it returns null if passed author is null or if author name is empty or
// returns empty list if author with such name does not exist in  list stored in hashmap
    public List<Book> getBookDetailsByAuthor(String author) {
        List<Book> authorbooklist = new ArrayList<>();
        if (author == null || author.trim().isEmpty()) {
            return null;
        }
       for(Book book :booksList.values()){
           if(book.getAuthor().equalsIgnoreCase(author)){
               logger.debug("found book with author name");
               authorbooklist.add(book);
           }
       }
       if(authorbooklist.isEmpty()) {
           logger.debug("no book exist with given author name" + author);
           return authorbooklist;
       }
       return authorbooklist;
    }

    public List<Book> getBookDetailsByGenre(String genre) {
        List<Book> genrebooklist = new ArrayList<>();
        if (genre == null || genre.trim().isEmpty()) {
            return null;
        }
        for(Book book :booksList.values()){
            if(book.getGenre().equalsIgnoreCase(genre)){
                logger.debug("found book with genre name");
                genrebooklist.add(book);
            }
        }
        if(genrebooklist.isEmpty()) {
            logger.debug("no book exist with given genre name" + genre);
            return genrebooklist;
        }
        return genrebooklist;
    }
    public  List<Book> getSortedBooksAlphabetically(){
      Set<Map.Entry<String, Book>> entries =  booksList.entrySet();
      List<Book> sortedBookList = entries.stream()
                      .sorted((e1, e2) -> e1.getValue().getAuthor().compareTo(e2.getValue().getAuthor()))
                      .map(e -> e.getValue())
                      .collect(Collectors.toList());
      return sortedBookList;
    }
}
