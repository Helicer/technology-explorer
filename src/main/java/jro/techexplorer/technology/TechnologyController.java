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
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;

    @RequestMapping(value="/")
    public String home(Model model) {
        return "redirect:/technology/list";
    }

    // LIST ALL
    @RequestMapping(value= "/technology/list")
    public String listAll(Model model) {
        List<Technology> technologies = technologyService.findAll();
        model.addAttribute("technologies", technologies);
        return "technology/technology_list";

    }

    // VIEW DETAIL
    @RequestMapping(value = "/technology/{id}")
    public String view(@PathVariable Long id, Model model) {
        Technology technology = technologyService.findById(id);
        if (technology != null) {
            model.addAttribute("technology", technology);
            return "technology/technology_view";
        } else {
            return "redirect:/technology/list";
        }
    }

    // ADD (form)
    @RequestMapping(value = "/technology/add")
    public String add(Model model) {
        Technology technology = new Technology();
        model.addAttribute("technology", technology);
        return "technology/technology_edit";
    }

    // EDIT (form)
    @RequestMapping(value = "/technology/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        // TODO: Fix Optional not rendering in tempalte
        Technology technology = technologyService.findById(id);
        model.addAttribute("technology", technology);
        return "technology/technology_edit";
    }

    // SAVE (POST)
    @PostMapping(value = "/technology")
    public String save(Model model, Technology technology) {
        technologyService.save(technology);
        model.addAttribute("technology", technology);
        return "redirect:/technology/" + technology.id;
    }


    // DELETE
    @PostMapping(value = "/technology/{id}/delete")
    public String delete(@PathVariable Long id) {
        Technology technology = technologyService.findById(id);
        if (technology != null) {
            technologyService.delete(id);
            return "redirect:/technology/list";
         } else {
            throw new ResourceNotFoundException();
        }
    }
}
