package hw6; 
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1: // Add Book
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();
                        
                        try {
                            library.addBook(new Book(title, author, isbn));
                            System.out.println("Book added successfully!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                        
                    case 2: // Remove Book
                        System.out.print("Enter ISBN of book to remove: ");
                        String removeIsbn = scanner.nextLine();
                        if (library.removeBook(removeIsbn)) {
                            System.out.println("Book removed successfully.");
                        } else {
                            System.out.println("Book not found with that ISBN.");
                        }
                        break;
                        
                    case 3: // Display All Books
                        System.out.println("\nAll Books in Library:");
                        library.displayAllBooks();
                        break;
                        
                    case 4: // Search by Title
                        System.out.print("Enter title to search: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> titleResults = library.searchByTitle(searchTitle);
                        displaySearchResults(titleResults, "title", searchTitle);
                        break;
                        
                    case 5: // Search by Author
                        System.out.print("Enter author to search: ");
                        String searchAuthor = scanner.nextLine();
                        List<Book> authorResults = library.searchByAuthor(searchAuthor);
                        displaySearchResults(authorResults, "author", searchAuthor);
                        break;
                        
                    case 6: // Check Out Book
                        System.out.print("Enter ISBN of book to check out: ");
                        String checkoutIsbn = scanner.nextLine();
                        if (library.checkOutBook(checkoutIsbn)) {
                            System.out.println("Book checked out successfully.");
                        } else {
                            System.out.println("Book not available or not found.");
                        }
                        break;
                        
                    case 7: // Return Book
                        System.out.print("Enter ISBN of book to return: ");
                        String returnIsbn = scanner.nextLine();
                        if (library.returnBook(returnIsbn)) {
                            System.out.println("Book returned successfully.");
                        } else {
                            System.out.println("Book wasn't checked out or not found.");
                        }
                        break;
                        
                    case 8: // Exit
                        System.out.println("Exiting Library Management System. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please enter 1-8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1-8).");
            }
        }
    }
    
    private static void displaySearchResults(List<Book> results, String searchType, String query) {
        if (results.isEmpty()) {
            System.out.println("No books found with " + searchType + ": " + query);
        } else {
            System.out.println("\nBooks found with " + searchType + " '" + query + "':");
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }
}