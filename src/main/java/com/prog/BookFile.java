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

    public void sortByDate() {
        bookList.sort((a, b) -> {
            if (a.getDate().isEmpty())
                return 1;
            if (b.getDate().isEmpty())
                return -1;
            String[] partsA = a.getDate().split("/");
            String[] partsB = b.getDate().split("/");
            String dateA = partsA[2] + partsA[1] + partsA[0];
            String dateB = partsB[2] + partsB[1] + partsB[0];
            return dateB.compareTo(dateA);
        });
    }

    /*----------------------------PERSISTENCE------------------------------ */
    public ArrayList<Book> readFile() throws Exception {
        ArrayList<Book> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("books.txt"))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                try {
                    String[] parts = line.split(",", -1);
                    int id = Integer.parseInt(parts[0]);
                    int rating = Integer.parseInt(parts[3]);
                    Book book = new Book(id, parts[1], parts[2], rating, parts[4], parts[5], parts[6], parts[7]);
                    list.add(book);
                } catch (Exception e) {
                    System.out.println("Error al leer línea: " + line);
                }
            }
        }
        return list;
    }

    public void writeFile(Book book) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt", true))) {
            bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getRating()
                    + "," + book.getNotes() + "," + book.getDate() + "," + book.getStatus() + "," + book.getCoverURL());
            bw.newLine();
        }
    }

    public void rewriteFile() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : bookList) {
                bw.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getRating()
                        + "," + book.getNotes() + "," + book.getDate() + "," + book.getStatus() + ","
                        + book.getCoverURL());
                bw.newLine();
            }
        }
    }

    public void sortByTitle() {
        bookList.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }
}