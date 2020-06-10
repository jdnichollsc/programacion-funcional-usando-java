package co.s4n.immutableobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Writer {

    private final String firstName;
    private final String lastName;
    private final List<Book> books;

    public Writer(String firstName, String lastName, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = Collections.unmodifiableList(books);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Writer addNewBook(Book newBook) {
        List<Book> books = new ArrayList<>(getBooks());
        books.add(newBook);
        return new Writer(firstName, lastName, books);
    }

    public Writer changeBookPrice(Book book, Double newPrice) throws Exception {
        List<Book> books = new ArrayList<>(getBooks());
        List<Book> updatedBooks = new ArrayList<>();
        if (books.contains(book)) {
            for (Book b : books) {
                if (b.getTitle().equals(book.getTitle())) {
                    updatedBooks.add(b.changePrice(newPrice));
                } else {
                    updatedBooks.add(b);
                }
            }
            return new Writer(firstName, lastName, updatedBooks);
        } else {
            throw new Exception(String.format("Book %s does not exist for the writer %s", book.getTitle(), firstName));
        }
    }
}
