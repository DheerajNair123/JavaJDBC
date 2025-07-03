package bookstore.dao;

import bookstore.model.Book;
import bookstore.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDAOImpl implements BookDAO {
    public int insert(Book book) {
        try {
            String sql = "insert into books(name, price) values(?, ?)";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setFloat(2, book.getPrice());
            int res = 0;
            res = preparedStatement.executeUpdate();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Book book) {
        String sql = "UPDATE books SET price = ? , name= ? WHERE id = ?";
        try (Scanner scanner = new Scanner(System.in);
             PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql)) {
            System.out.println("Enter name to update");
            String newName = scanner.nextLine();
            System.out.println("Enter name to update");
            float newPrice = scanner.nextFloat();
            preparedStatement.setString(2, newName);
            preparedStatement.setFloat(3, newPrice);
            int res = 0;
            res = preparedStatement.executeUpdate();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("Book with ID " + id + " has been deleted.");
                return 1;  // Indicates success
            } else {
                System.out.println("No book with ID " + id + " was found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  // Indicates failure
    }


    @Override
    public List<Book> view() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = DBUtil.getConnection().createStatement();
            String sql = "select * from books";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
                books.add(new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3))
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Book view(int id) {
        Book book = null;
        try {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


    public List<Book> view(String name) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE name = ?";
        try (PreparedStatement preparedStatement = DBUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(new Book(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getFloat(3)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
