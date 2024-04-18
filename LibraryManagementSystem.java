import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryManagementSystem {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.run();
    }

    public void run() {
        // Books in library
        books.add(new Book(1, "Encyclopedia Of Life", "Miles Kelly", 2017, true));
        books.add(new Book(2, "Matilda", "Roald Dahl", 1988, true));
        books.add(new Book(3, "Diary of a Wimpy Kid", "Jeff Kinney", 2007, true));
        books.add(new Book(4, "Secrets of a Champion", "Jannie Putter", 2004, true));
        books.add(new Book(5, "Fallen", "Lauren Kate", 2009, true));
        books.add(new Book(6, "One Hundred Years of Solitude", "Gabriel García Márquez", 1967, true));
        books.add(new Book(7, "The Great Gatsby", "F. Scott Fitzgerald", 1925, true));
        books.add(new Book(8, "To Kill a Mockingbird", "Harper Lee", 1960, true));
        books.add(new Book(9, "The Birth of Tragedy", "Friedrich Nietzsche", 1872, true));

        // Library members
        members.add(new Member("Thabo Maputla", "thabo.maputla@gmail.com", ""));
        members.add(new Member("Elsa Montana", "elsa.montana@outlook.com", ""));
        members.add(new Member("Jake Le Roux", "jake.ler@outlook.com", ""));
        members.add(new Member("Mbali Smith", "smith.mbali@gmail.com", ""));
        members.add(new Member("Sipho Mbeki", "sipho.be@gmail.com", ""));
        members.add(new Member("Jan Smit", "jan.smit@gmail.com", ""));

        System.out.println("Library Management System");
        while (true) {
            // Library Menu
            System.out.println("1. Manage books ");
            System.out.println("2. Manage members ");
            System.out.println("3. Add new book ");
            System.out.println("4. Search for book by title or author");
            System.out.println("5. Register new member");
            System.out.println("6. Check out book ");
            System.out.println("7. Exit ");

            // Read user input
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            // Actions based on user choice
            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                    manageMembers();
                    break;
                case 3:
                    addNewBook();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    registerMember();
                    break;
                case 6:
                    checkoutBook();
                    break;
                case 7:
                    System.out.println("Exiting Program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }

    private void manageBooks() {
        System.out.println("Manage books ");
        System.out.println("1. Update book ");
        System.out.println("2. Delete book ");
        System.out.println("3. Display all books ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                updateBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                displayAllBooks();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                break;
        }
    }

    private void updateBook() {
        System.out.println("Enter the ISBN of the book to update: ");
        int bookISBNToUpdate;
        try {
            bookISBNToUpdate = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID.");
            return;
        }

        // Finding the book in the list
        Book bookToUpdate = null;
        for (Book book : books) {
            if (book.getBookISBN() == bookISBNToUpdate) {
                bookToUpdate = book;
                break;
            }
        }
        if (bookToUpdate == null) {
            System.out.println("Book searched not found.");
            return;
        }

        // Prompt for new details
        System.out.println("Enter new details for the book:");
        System.out.print("Title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Author: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Year: ");
        int newYear;
        try {
            newYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Year. Please enter a valid number.");
            return;
        }

        // Updating the book properties
        bookToUpdate.setTitle(newTitle);
        bookToUpdate.setAuthor(newAuthor);
        bookToUpdate.setYear(newYear);

        System.out.println("Book updated successfully.");
    }

    private void deleteBook() {
        System.out.println("Enter the ISBN of the book to delete: ");
        int bookISBNToDelete;
        try {
            bookISBNToDelete = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid book ID.");
            return;
        }

        // Finding the book in the list
        Book bookToDelete = null;
        for (Book book : books) {
            if (book.getBookISBN() == bookISBNToDelete) {
                bookToDelete = book;
                break;
            }
        }
        if (bookToDelete == null) {
            System.out.println("Book not found.");
            return;
        }

        // Remove the book
        books.remove(bookToDelete);
        System.out.println("Book deleted successfully.");
    }

    private void displayAllBooks() {
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println("ISBN: " + book.getBookISBN());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Year: " + book.getYear());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println();
        }
    }

    private void manageMembers() {
        System.out.println("Manage members:");
        System.out.println("1. Display all members");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                listAllMembers();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1");
                break;
        }
    }

    private void listAllMembers() {
        System.out.println("All Members:");
        for (Member member : members) {
            System.out.println(member);
            System.out.println();
        }
    }


    private void addNewBook() {
        System.out.println("\nEnter book details:");
        System.out.print("Book ID: ");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Book ID. Please enter a valid number.");
            return;
        }

        System.out.print("Title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Author: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Year: ");
        int newYear;
        try {
            newYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Year. Please enter a valid number.");
            return;
        }

        Book newBook = new Book(bookId, newTitle, newAuthor, newYear, true);
        books.add(newBook);

        System.out.println("Book added successfully.");
    }

    private void searchBook() {
        System.out.println("Search for book by title or author:");
        String searchQuery = scanner.nextLine().toLowerCase(); // Converting user input to lowercase for case-insensitive search

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchQuery) || book.getAuthor().toLowerCase().contains(searchQuery)) {
                System.out.println("Book found:");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Year: " + book.getYear());
                System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private void registerMember() {
        System.out.println("Register new member:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        } while (!isValidEmail(email));

        // Creating a new member
        Member newMember = new Member(name, email, "");
        members.add(newMember);

        System.out.println("Member registered successfully.");
    }

    private void checkoutBook() {
        System.out.println("Check out book:");
        System.out.print("Enter member email: ");
        String email = scanner.nextLine();

        // Find the member
        Member member = null;
        for (Member m : members) {
            if (m.getEmail().equals(email)) {
                member = m;
                break;
            }
        }

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        // Prompt for book ID
        System.out.print("Enter book ID to check out: ");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for Book ID. Please enter a valid number.");
            return;
        }

        // Find the book
        Book bookToCheckOut = null;
        for (Book book : books) {
            if (book.getBookISBN() == bookId && book.isAvailable()) {
                bookToCheckOut = book;
                break;
            }
        }

        if (bookToCheckOut == null) {
            System.out.println("Book not found or not available for checkout.");
            return;
        }

        // Updating member's borrowed book and book availability
        member.setBorrowedBook(bookToCheckOut.getTitle());
        bookToCheckOut.setAvailable(false);

        System.out.println("Book checked out successfully.");
    }

    static class Book {
        private int bookISBN;
        private String title;
        private String author;
        private int year;
        private boolean available;

        // Constructor
        public Book(int bookISBN, String title, String author, int year, boolean available) {
            this.bookISBN = bookISBN;
            this.title = title;
            this.author = author;
            this.year = year;
            this.available = available;
        }

        // Getters and setters
        public int getBookISBN() {
            return bookISBN;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Member {
        private String name;
        private String email;
        private String borrowedBook;

        public Member(String name, String email, String borrowedBook) {
            this.name = name;
            this.email = email;
            this.borrowedBook = borrowedBook;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBorrowedBook() {
            return borrowedBook;
        }

        public void setBorrowedBook(String borrowedBook) {
            this.borrowedBook = borrowedBook;
        }

        public String toString() {
            return "Name: " + name + "\nEmail: " + email + "\nBorrowed Book: " + borrowedBook;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
