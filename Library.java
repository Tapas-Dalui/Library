import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Book class to represent each book
class Book {
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true; // Initially all books are available
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Patron class to represent each patron
class Patron {
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Patron(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false); // Mark the book as unavailable when borrowed
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true); // Mark the book as available when returned
    }
}

// Library class to manage books, patrons, and borrowing records
public class Library {
    private Map<String, Book> books; // Map to store books by title
    private Map<String, Patron> patrons; // Map to store patrons by name

    public Library() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
    }

    public void addBook(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        books.put(title, book);
    }

    public void addPatron(String name, String contactInfo) {
        Patron patron = new Patron(name, contactInfo);
        patrons.put(name, patron);
    }

    public void borrowBook(String patronName, String bookTitle) {
        Patron patron = patrons.get(patronName);
        Book book = books.get(bookTitle);
        if (patron != null && book != null && book.isAvailable()) {
            patron.borrowBook(book);
            System.out.println(bookTitle + " has been borrowed by " + patronName);
        } else {
            System.out.println("Book not available or patron not found.");
        }
    }

    public void returnBook(String patronName, String bookTitle) {
        Patron patron = patrons.get(patronName);
        Book book = books.get(bookTitle);
        if (patron != null && book != null && patron.getBorrowedBooks().contains(book)) {
            patron.returnBook(book);
            System.out.println(bookTitle + " has been returned by " + patronName);
        } else {
            System.out.println("Book not borrowed by patron or patron not found.");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        // Adding books
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "Classic");
        library.addBook("To Kill a Mockingbird", "Harper Lee", "Fiction");

        // Adding patrons
        library.addPatron("John Doe", "john@example.com");
        library.addPatron("Jane Smith", "jane@example.com");

        // Borrowing and returning books
        library.borrowBook("John Doe", "The Great Gatsby");
        library.borrowBook("Jane Smith", "To Kill a Mockingbird");

        library.returnBook("John Doe", "The Great Gatsby");
    }
}
