package ru.lkodos.library.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lkodos.library.dao.BookDao;
import ru.lkodos.library.dao.PersonDao;
import ru.lkodos.library.model.Book;
import ru.lkodos.library.model.Person;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String allBooksPage(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "books/books";
    }

    @GetMapping("/new")
    public String createNewBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") Integer id, Model model, @ModelAttribute("newPerson") Person newPerson) {

        model.addAttribute("people", personDao.findAll());

        Book book = bookDao.getById(id);
        model.addAttribute("book", book);

        Integer personId = book.getPersonId();
        if (personId != null) {
            Person person = personDao.getById(personId);
            model.addAttribute("person", person);
        }

        return "books/book";
    }

    @PatchMapping("/{id}")
    public String releaseBook(@PathVariable("id") int id) {
        bookDao.releaseBook(id);
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/issue")
    public String issueBook(@PathVariable("id") int id, @ModelAttribute("newPerson") Person newPerson) {

        bookDao.issueBook(id, newPerson);

        return "redirect:/books/{id}";
    }

    @GetMapping("/edit/{id}")
    public String editBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getById(id));
        return "books/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}
