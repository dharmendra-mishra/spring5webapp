package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher pub = new Publisher("Pearson", "Street1", "Lucknow", "UP", "231231");
        publisherRepository.save(pub);

        Author eric = new Author("Eric ", "John");
        Book physics = new Book("Physics", "123");
        eric.getBooks().add(physics);
        physics.getAuthors().add(eric);

        physics.setPublisher(pub);
        pub.getBooks().add(physics);

        authorRepository.save(eric);
        bookRepository.save(physics);
        publisherRepository.save(pub);

        Author bob = new Author("Bob ", "Marley");
        Book cse = new Book("CSE", "123");
        bob.getBooks().add(cse);
        cse.getAuthors().add(bob);

        cse.setPublisher(pub);
        pub.getBooks().add(cse);

        authorRepository.save(bob);
        bookRepository.save(cse);
        publisherRepository.save(pub);

        System.out.println("Started in Bootstrap");
        System.out.println("Num of books = " + bookRepository.count());

        Publisher pub2 = new Publisher("TestPub", "Street1", "Lucknow", "UP", "231231");
        publisherRepository.save(pub2);
        System.out.println("Num of publisher = " + publisherRepository.count());
        System.out.println("Num of books by pub = " + pub.getBooks().size());
        System.out.println("Num of books by pub2 = " + pub2.getBooks().size());
    }
}
