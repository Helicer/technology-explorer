package jro.techexplorer.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;


@Controller
public class TechnologyController {

    @Autowired
    TechnologyService technologyService;

    // LIST ALL
    @RequestMapping(value= "/technology/list")
    public String listAll(Model model) {
        List<Technology> technologies = technologyService.findAll();
        model.addAttribute("technology_list", technologies);
        return "technology/technology_list";

    }

    // VIEW DETAIL
    @RequestMapping(value = "/technology/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<Technology> technology = technologyService.findById(id);
        model.addAttribute("technology", technology);
        return "technology/technology_view";
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
        Optional<Technology> technology = technologyService.findById(id);
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
    @RequestMapping(value = "/technology/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        technologyService.delete(id);
        List<Technology> technologies = technologyService.findAll();
        model.addAttribute("technology_list", technologies);
        // TODO: Redirect to List?
        return "technology_list";
    }
}
