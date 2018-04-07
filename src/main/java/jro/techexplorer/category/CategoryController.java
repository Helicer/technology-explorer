package jro.techexplorer.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // LIST ALL
    @RequestMapping(value="/")
    public String listAll(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return ("category/list");
    }

    // VIEW DETAIL
    @RequestMapping(value = "/{id}")
    public String view(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        if (category != null) {
            model.addAttribute("category", category);
            return "category/view";
        } else {
            return "redirect:/categories/";
        }
    }

    // ADD (form)
    @RequestMapping(value = "/add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/edit";
    }

    // EDIT (form)
    @RequestMapping(value = "/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    // SAVE (POST)
    @PostMapping(value = "/")
    public String save(Model model, Category category) {
        categoryService.save(category);
        model.addAttribute("category", category);
        return "redirect:/categories/" + category.id;
    }



}
