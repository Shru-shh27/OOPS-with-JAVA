package com.library.service;

import com.library.books.Book;
import com.library.exception.*;

import java.io.*;
import java.util.*;

public class LibraryService {

    private static final String FILE_NAME = "books.txt";

    // Add Book
    public void addBook(Book b) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            bw.write(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getAvailableCopies());
            bw.newLine();

            System.out.println("Book Added Successfully");

        } catch (IOException e) {
            System.out.println("Error writing file");
        }

    }

    public void viewBooks() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                Book b = new Book(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Integer.parseInt(data[3]));

                b.displayBook();
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

    }

    public void issueBook(int bookId) throws BookNotFoundException, BookNotAvailableException {

        List<Book> books = readBooks();
        boolean found = false;

        for (Book b : books) {

            if (b.getBookId() == bookId) {

                found = true;

                if (b.getAvailableCopies() == 0) {
                    throw new BookNotAvailableException("Book not available");
                }

                b.setAvailableCopies(b.getAvailableCopies() - 1);
                break;
            }
        }

        if (!found) {
            throw new BookNotFoundException("Book ID not found");
        }

        writeBooks(books);
        System.out.println("Book Issued Successfully");
    }

    public void returnBook(int bookId) throws BookNotFoundException {

        List<Book> books = readBooks();
        boolean found = false;

        for (Book b : books) {

            if (b.getBookId() == bookId) {

                found = true;
                b.setAvailableCopies(b.getAvailableCopies() + 1);
                break;
            }
        }

        if (!found) {
            throw new BookNotFoundException("Book ID not found");
        }

        writeBooks(books);
        System.out.println("Book Returned Successfully");
    }

    private List<Book> readBooks() {

        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                books.add(new Book(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Integer.parseInt(data[3])));
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        return books;
    }

    private void writeBooks(List<Book> books) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Book b : books) {

                bw.write(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.getAvailableCopies());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file");
        }

    }
}