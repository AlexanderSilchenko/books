package dz.SpringTwoCRUD.books.controllers;

import dz.SpringTwoCRUD.books.dao.BookDAO;
import dz.SpringTwoCRUD.books.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Здесь описывает контроллер, который манипулирует сущностью Book с помощью CRUD операций

@Controller
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookDAO bookDAO;

    @GetMapping
    public String index(Model model) {
        List<Book> bookList = bookDAO.allBooks();
        //После добавления списка в модель он будет виден в нашем view
        model.addAttribute("books", bookList);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "books/show";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    //@ModelAttribute нам даёт готовый объект recipe из нашего представления (recipes/new.html)
    //Нужны обязательно set методы для класса Recipe!!!!
    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.getBook(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    //Patch запрос используется для изменения ресурса
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

}



