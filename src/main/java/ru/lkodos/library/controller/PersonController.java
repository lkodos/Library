package ru.lkodos.library.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lkodos.library.dao.BookDao;
import ru.lkodos.library.dao.PersonDao;
import ru.lkodos.library.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDao personDao;
    private final BookDao bookDao;

    @Autowired
    public PersonController(PersonDao personDao, BookDao bookDao) {
        this.personDao = personDao;
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String allPeoplePage(Model model) {
        model.addAttribute("people", personDao.findAll());
        return "people/people";
    }

    @GetMapping("/new")
    public String createNewPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        model.addAttribute("books", bookDao.getAllByPersonId(id));
        return "people/person";
    }

    @GetMapping("/edit/{id}")
    public String editPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }
}
