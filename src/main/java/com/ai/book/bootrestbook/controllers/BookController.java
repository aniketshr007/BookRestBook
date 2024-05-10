package com.ai.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ai.book.bootrestbook.entities.Book;
import com.ai.book.bootrestbook.services.BookService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    
    //GET ALL BOOKS
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list=bookService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    //GET BOOK
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book= bookService.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    //ADD BOOK
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
       Book b =null;
       try{
         b = this.bookService.addBook(book);
        return ResponseEntity.of(Optional.of(b));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    //DELETE BOOK
    @DeleteMapping("/books/{bookid}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookid") int id){
        try{
            this.bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
      
    }
    
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        this.bookService.updateBook(book,id);
        return book;
    }
}
