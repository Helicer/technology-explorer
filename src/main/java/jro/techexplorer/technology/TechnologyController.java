package jro.techexplorer.technology;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;



@Controller
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @RequestMapping(value="/")
    public String home() {
        return "redirect:/technologies/list";
    }

    // LIST ALL
    @RequestMapping(value= "/list")
    public String listAll(Model model) {
        List<Technology> technologies = technologyService.findAll();
        model.addAttribute("technologies", technologies);
        return "technology/technology_list";

    }

    // VIEW DETAIL
    @RequestMapping(value = "/{id}")
    public String view(@PathVariable Long id, Model model) {
        Technology technology = technologyService.findById(id);
        if (technology != null) {
            model.addAttribute("technology", technology);
            return "technology/technology_view";
        } else {
            return "redirect:/technologies/list";
        }
    }

    // ADD (form)
    @RequestMapping(value = "/add")
    public String add(Model model) {
        Technology technology = new Technology();
        model.addAttribute("technology", technology);
        return "technology/technology_edit";
    }

    // EDIT (form)
    @RequestMapping(value = "/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        Technology technology = technologyService.findById(id);
        model.addAttribute("technology", technology);
        return "technology/technology_edit";
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
            return "redirect:/technologies/list";
         } else {
            throw new ResourceNotFoundException();
        }
    }
}
