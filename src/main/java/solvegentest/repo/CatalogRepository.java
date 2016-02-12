package solvegentest.repo;

import org.springframework.stereotype.Component;
import solvegentest.generated.Book;
import solvegentest.generated.Catalog;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.kvitko on 11.02.2016.
 * Repository of web service data.
 */
@Component
public class CatalogRepository {

    private static final Catalog catalog = new Catalog();

    public CatalogRepository() {

    }

    @PostConstruct
    public void initData() {

        ArrayList<Book> books = new ArrayList<>();

        Book bk101 = new Book();
        bk101.setId("bk101");
        bk101.setAuthor("Gambardella, Matthew");
        bk101.setTitle("XML Developer's Guide");
        bk101.setGenre("Computer");
        bk101.setPrice(44.95);
        bk101.setPublishDate("2000-10-01");
        bk101.setDescription("An in-depth look at creating applications with XML.");
        books.add(bk101);

        Book bk102 = new Book();
        bk102.setId("bk102");
        bk102.setAuthor("Ralls, Kim");
        bk102.setTitle("Midnight Rain");
        bk102.setGenre("Fantasy");
        bk102.setPrice(4.95);
        bk102.setPublishDate("2000-12-16");
        bk102.setDescription("A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen" +
                " of the world.");
        books.add(bk102);

        catalog.getBooks().addAll(books);

    }

    /**
     * Update book catalog by rules: if book exist  - update attributes else add new book
     * to catalog
     *
     * @param pBook - Book
     *             return catalog - ArrayList<Book>
     */

    public Catalog updateCatalog(Book pBook) {
        if ( pBook == null || pBook.getId().isEmpty() || pBook.getId().equals("?") ){
            return catalog;
        }
        if ( pBook.getAuthor().isEmpty()
                && pBook.getTitle().isEmpty()
                && pBook.getGenre().isEmpty()
                && pBook.getPublishDate().isEmpty()
                && pBook.getDescription().isEmpty() ){
            removeBookFromCatalog( pBook.getId() );
          return catalog;
        }
        boolean isNewBook = true;
        for ( Book book : catalog.getBooks() ){
            if ( book.getId().equals( pBook.getId() ) ){
                book.setAuthor( pBook.getAuthor() );
                book.setTitle( pBook.getTitle() );
                book.setGenre( pBook.getGenre() );
                book.setPrice( pBook.getPrice() );
                book.setPublishDate( pBook.getPublishDate() );
                book.setDescription( pBook.getDescription() );
                isNewBook = false;
                break;
            }
        }
        if ( isNewBook ){
            catalog.getBooks().add( pBook );
        }
        return catalog;
    }
    /**
     * Remove book from catalog by id
     * @param bookId - String
     */
    private void removeBookFromCatalog( String bookId ){
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : catalog.getBooks() ){
            if ( !book.getId().equals( bookId ) ){
                books.add( book );
            }
        }
        catalog.getBooks().removeAll( catalog.getBooks() );
        catalog.getBooks().addAll( books );
    }

}
