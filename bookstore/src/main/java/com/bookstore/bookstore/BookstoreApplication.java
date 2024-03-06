package com.bookstore.bookstore;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.bookstore.Book.Book;
import com.bookstore.bookstore.Book.BookRepository;
import com.bookstore.bookstore.Book.Category;
import com.bookstore.bookstore.Book.CategoryRepository;

import ch.qos.logback.classic.Logger;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = (Logger) LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository drepository){
		return (args) -> {

			log.info("save a couple of books");

			drepository.save(new Category("Thriller"));
			drepository.save(new Category("Dystopia"));
			drepository.save(new Category("Poem"));


			repository.save(new Book( "Ernest Hemingway", "A Farewell to Arms","123-123", 1929, 15, drepository.findByName("Thriller").get(0)));

			repository.save(new Book( "George Orwell", "Animal Farm","321-321", 1945, 12, drepository.findByName("Dystopia").get(0)));

			repository.save(new Book("Murakami", "Norwegian wood","321-321", 2008, 12, drepository.findByName("Thriller").get(0)));

			repository.save(new Book("edgar poe", "raven","321-321", 1930, 5, drepository.findByName("Poem").get(0)));

			log.info("fetch all books");
			for(Book book : repository.findAll()){
				log.info(book.toString());
			}



		};


	}
}
