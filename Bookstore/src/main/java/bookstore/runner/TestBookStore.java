package bookstore.runner;

import bookstore.dao.BookDAO;
import bookstore.dao.BookDAOImpl;
import bookstore.model.Book;

public class TestBookStore {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();
//        TODO: Insert a new book using DAO
//        int res = bookDAO.insert(new Book(0, "Thinking in Java", 350.5f));
//        System.out.println(res + " books inserted");

//        int res2 = bookDAO.delete(2);
//        System.out.println(res2 + " books inserted");

//        TODO: View Books
//        bookDAO.view().forEach(System.out::println);
        Book b = bookDAO.view(1);
        System.out.println(b);
        //TODO: Update book
//        int res1 = bookDAO.update(new Book(0, "OCJP", 500f));
//        System.out.println(res1 + " books updated");

    }
}
