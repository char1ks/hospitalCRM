package controller;

import model.Child;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.childService;
import service.personService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/child")
public class childController {
    private childService operations;
    private personService operations2;
    @Autowired
    public void setOperations(childService operations) {
        this.operations = operations;
    }
    @Autowired
    public void setOperations2(personService operations2) {
        this.operations2 = operations2;
    }

    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("childs",operations.getAllChild());
        return "child/mainPage";
    }
    @GetMapping("/{id}")
    public String concretPage(@PathVariable("id")int id,
                              Model model){
        model.addAttribute("child",operations.getConcretChild(id));
        return "child/concretPage";
    }
    //ADD
    @GetMapping("/new")
    public String addPage(@ModelAttribute("newChild") Child child){
        return "child/newPage";
    }
    @PostMapping
    public String addInDB(@ModelAttribute("newChild") @Valid Child child,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "child/newPage";
        }
        operations.saveChild(child);
        return "redirect:/child";
    }
    //EDIT
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id,
                           Model model){
        model.addAttribute("concretPerson",operations.getConcretChild(id));
        return "child/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDb(@ModelAttribute("concretPerson") @Valid Child child,
                           BindingResult bindingResult,@PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "child/editPage";
        operations.updateChild(id,child);
        return "redirect:/child";
    }
    //DELETE
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        operations.deleteChild(id);
        return "redirect:/child";
    }
    //ANYMORE
    @GetMapping("/chooseChild/{personId}")
    public String chooseChildPage(@PathVariable("personId") int personId,Model model){
        model.addAttribute("personId",personId);
        model.addAttribute("allChild",operations.getAllChildWherePartnerIdNULL());
        return "child/chooseChild";
    }
    @PatchMapping("/{personId}/{childId}")
    public String insertIdInChild(@PathVariable("personId") int personId,
                                  @PathVariable("childId") int childId){
        Optional<Child> child=operations.getConcretChild(childId);
        Optional<Person> person=operations2.getConcretPerson(personId);
        child.get().setPerson(person.get());
        operations.updateChild(childId,child.get());
        return "redirect:/child/chooseChild/"+personId;
    }
}
