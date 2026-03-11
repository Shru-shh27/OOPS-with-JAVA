package com.library.main;

import com.library.books.Book;
import com.library.service.LibraryService;
import com.library.exception.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            LibraryService service = new LibraryService();

            int choice;

            do {

                System.out.println("\n===== Library Menu =====");
                System.out.println("1 Add Book");
                System.out.println("2 View Books");
                System.out.println("3 Issue Book");
                System.out.println("4 Return Book");
                System.out.println("5 Exit");

                choice = sc.nextInt();

                try {

                    switch (choice) {

                        case 1:

                            System.out.print("Book ID: ");
                            int id = sc.nextInt();

                            sc.nextLine();

                            System.out.print("Title: ");
                            String title = sc.nextLine();

                            System.out.print("Author: ");
                            String author = sc.nextLine();

                            System.out.print("Copies: ");
                            int copies = sc.nextInt();

                            service.addBook(new Book(id, title, author, copies));
                            break;

                        case 2:
                            service.viewBooks();
                            break;

                        case 3:
                            System.out.print("Enter Book ID: ");
                            service.issueBook(sc.nextInt());
                            break;

                        case 4:
                            System.out.print("Enter Book ID: ");
                            service.returnBook(sc.nextInt());
                            break;

                        case 5:
                            System.out.println("Exiting...");
                            break;
                    }

                } catch (BookNotFoundException | BookNotAvailableException e) {

                    System.out.println("Error: " + e.getMessage());

                }

            } while (choice != 5);
        }

    }
}