package dz.SpringTwoCRUD.books.dao;

import dz.SpringTwoCRUD.books.models.Book;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:G:\\IdeaProjects\\books\\database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Вернуть список всех рецептов
    //READ
    public List<Book> allBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Book";
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                int id = set.getInt("id");
                String writer = set.getString("writer");
                String title = set.getString("title");
                String genre = set.getString("genre");
                books.add(new Book(id, writer, title, genre));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }
    //READ
    public Book getBook(int id) {
        try {
            String query = "SELECT * FROM Book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int bookID = set.getInt("id");
                String writer = set.getString("writer");
                String title = set.getString("title");
                String genre = set.getString("genre");
                return new Book(bookID, writer, title, genre);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //CREATE
    public void addBook(Book book) {
        try {
            String query = "INSERT INTO Book (writer, title, genre) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, book.getWriter());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getGenre());
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    //UPDATE
    public void updateBook(int id, Book updatedBook) {
        try {
            String query = "UPDATE Book SET writer = ?, title = ?, genre = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedBook.getWriter());
            statement.setString(2, updatedBook.getTitle());
            statement.setString(3, updatedBook.getGenre());
            statement.setInt(4, id);
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    //DELETE
    public void deleteBook(int id) {
        try {
            String query = "DELETE FROM Book WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

}
