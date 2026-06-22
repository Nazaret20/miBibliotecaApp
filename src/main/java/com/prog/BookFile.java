package com.prog;

import java.io.*;
import java.util.ArrayList;

public class BookFile {
    private ArrayList<Book> bookList;

    public BookFile() throws Exception {
        bookList = readFile();
    }

    /*----------------------------MODIFY------------------------------ */
    public void deleteBook(int id) throws Exception {
        for (Book book : bookList) {
            if (book.getId() == id) {
                bookList.remove(book);
                rewriteFile();
                break;
            }
        }
    }

    public void editBook(Book editedBook) throws Exception {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getId() == editedBook.getId()) {
                bookList.set(i, editedBook);
                rewriteFile();
                break;
            }
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    /*----------------------------PERSISTENCE------------------------------ */
    public ArrayList<Book> readFile() throws Exception {
        ArrayList<Book> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line = "";
            while (line != null) {
                line = br.readLine();
                if (line != null) {

                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    int rating = Integer.parseInt(parts[3]);
                    Book book = new Book(id, parts[1], parts[2], rating, parts[4], parts[5], parts[6]);
                    list.add(book);
                }
            }
        }
        return list;
    }

    public void writeFile(Book book) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt", true))) {
            bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getRating()
                    + "," + book.getNotes() + "," + book.getDate() + "," + book.getStatus());
            bw.newLine();
        }
    }

    public void rewriteFile() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : bookList) {
                bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getRating()
                        + "," + book.getNotes() + "," + book.getDate() + "," + book.getStatus());
                bw.newLine();
            }
        }
    }
}