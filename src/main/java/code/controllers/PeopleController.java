package code.controllers;

import code.dao.PersonDAO;
import code.models.Person;
import code.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
private final PersonDAO personDAO;
private final PersonValidator personValidator;
@Autowired
public PeopleController(PersonDAO personDAO, PersonValidator personValidator){
    this.personDAO=personDAO;
    this.personValidator = personValidator;
}
@GetMapping
    public String index(Model model){
    model.addAttribute("people",personDAO.index());
    return "people/index";
}
@GetMapping("{personId}")
    public String show(@PathVariable("personId") int personId, Model model){
    model.addAttribute("person",personDAO.show(personId));
    model.addAttribute("books",personDAO.getBooksByPersonId(personId));

    return "/people/show";
}


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")Person person){
    return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(Model model, @PathVariable("personId") int personId) {
        model.addAttribute("person", personDAO.show(personId));
        return "people/edit";
    }

    @PatchMapping("/{personId}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("personId") int personId) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(personId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{personId}")
    public String delete(@PathVariable("personId") int personId) {
        personDAO.delete(personId);
        return "redirect:/people";
    }
}
