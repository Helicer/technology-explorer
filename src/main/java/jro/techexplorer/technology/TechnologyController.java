package jro.techexplorer.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;


    // LIST ALL
    @RequestMapping(value= "/")
    public String listAll(Model model) {
        List<Technology> technologies = technologyService.findAll();
        model.addAttribute("technologies", technologies);
        return "technology/list";

    }

    // VIEW DETAIL
    @RequestMapping(value = "/{id}")
    public String view(@PathVariable Long id, Model model) {
        Technology technology = technologyService.findById(id);
        if (technology != null) {
            model.addAttribute("technology", technology);
            return "technology/view";
        } else {
            return "redirect:/technologies/";
        }
    }

    // ADD (form)
    @RequestMapping(value = "/add")
    public String add(Model model) {
        Technology technology = new Technology();
        model.addAttribute("technology", technology);
        return "technology/edit";
    }

    // EDIT (form)
    @RequestMapping(value = "/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Technology technology = technologyService.findById(id);
        model.addAttribute("technology", technology);
        return "technology/edit";
    }

    // SAVE (POST)
    @PostMapping(value = "/")
    public String save(Model model, Technology technology) {
        technologyService.save(technology);
        model.addAttribute("technology", technology);
        return "redirect:/technologies/" + technology.id;
    }


    // DELETE
    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable Long id) {
        Technology technology = technologyService.findById(id);
        if (technology != null) {
            technologyService.delete(id);
            return "redirect:/technologies/";
         } else {
            return "redirect:/technologies/";
        }
    }
}
