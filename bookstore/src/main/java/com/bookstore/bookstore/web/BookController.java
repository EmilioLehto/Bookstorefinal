package com.bookstore.bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookstore.bookstore.Book.Book;
import com.bookstore.bookstore.Book.BookRepository;
import com.bookstore.bookstore.Book.CategoryRepository;



@Controller
public class BookController {

@Autowired
private BookRepository repository;

@Autowired
private CategoryRepository drepository;


@RequestMapping("/booklist")
public String bookStoreList(Model model) {
    model.addAttribute("book", repository.findAll());
   

    return "booklist";
}

  @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> studentListRest() {	
        return (List<Book>) repository.findAll();
    }    

    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }      
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/delete/{id}", method=RequestMethod.GET)
public String deleteBook(@PathVariable("id") Long bookId, Model model ) {
    repository.deleteById(bookId);
    return "redirect:../booklist";
}

@RequestMapping(value="/add")
public String addBook(Model model) {
model.addAttribute("book", new Book());
model.addAttribute("category", drepository.findAll());
return "addStudent";

}


@RequestMapping(value="/save", method = RequestMethod.POST)
public String save(Book book) {
repository.save(book);
return "redirect::bookList";

}

@RequestMapping(value="/login")
public String login() {	
    return "login";
}	



}
