package dz.SpringTwoCRUD.books.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
//    id (целое число, первичный ключ, автоинкремент)
//    Автор (строка)
//    Название книги (строка)
//    Жанр (строка)

    private int id;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2, max = 50, message = "Не менее двух и не более 50 символов")
    @Pattern(regexp = "[А-Яа-я\\s.!]+", message = "Только кирилица")
    private String writer;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 100, message = "Не менее двух и не более 100 символов")
    @Pattern(regexp = "[А-Яа-я\\s.!]+", message = "Только кирилица")
    private String title;
    @NotEmpty(message = "Укажите жанр книги")
    @Size(min = 1, max = 20, message = "Не более 20 символов")
    @Pattern(regexp = "[А-Яа-я\\s]+", message = "Только кирилица")
    private String genre;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setWriter(String writer) {
//        this.writer = writer;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public String getWriter() {
//        return writer;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public Book() {
//
//    }
//
//    public Book(int id, String writer, String title, String genre) {
//        this.id = id;
//        this.writer = writer;
//        this.title = title;
//        this.genre = genre;
//    }
//
//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", writer='" + writer + '\'' +
//                ", title='" + title + '\'' +
//                ", genre='" + genre + '\'' +
//                '}';
//    }
}
