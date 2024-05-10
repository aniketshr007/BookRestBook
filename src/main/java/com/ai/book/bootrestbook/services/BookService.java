package com.ai.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.book.bootrestbook.dao.BookRepository;
import com.ai.book.bootrestbook.entities.Book;

@Component
public class BookService  {
    @Autowired
    private BookRepository bookRepository;
   // private static List<Book> list= new ArrayList<>();
    
    // static{
    //     list.add(new Book(12, "Java Complete Reference","XYZ"));
    //     list.add(new Book(13, "Head","ABC"));
    //     list.add(new Book(14, "Tail","LMN"));
    // }

    public List<Book> getAllBooks(){
        List <Book> list =(List<Book>) this.bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id){
        Book book=null;
        try{
             //book = list.stream().filter(e->e.getId()==id).findFirst().get();
              book = this.bookRepository.findById(id);
        }
        catch(Exception e){
            e.printStackTrace(); 
        }
        
        return book;
    }

    public Book addBook(Book book){
        //list.add(book);
        Book b=(Book) this.bookRepository.save(book);
        return b;
    }

    public void deleteBook(int id){
        //list = list.stream().filter(book ->book.getId() !=id).collect(Collectors.toList());
        this.bookRepository.deleteById(id);
    }

    public void updateBook(Book book, int id) {
    //    list = list.stream().map(b ->{
    //     if(b.getId()==id){
    //         b.setAuthor(book.getAuthor());
    //         b.setTitle(book.getTitle());
    //     }
    //     return b;
    //    }).collect(Collectors.toList());

   book.setId(id);

    this.bookRepository.save(book);
    }


}
