package com.redflags.catalogo.controller;

import com.redflags.catalogo.entity.RedFlag;
import com.redflags.catalogo.service.RedFlagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

import java.util.List;

@Controller
@RequestMapping("/")
public class CatalogController {

    private final RedFlagService redFlagService;

    public CatalogController(RedFlagService redFlagService) {
        this.redFlagService = redFlagService;
    }

    @GetMapping("")
    public String index(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
            Model model) {
        
        List<RedFlag> redflags;

        if (!search.isEmpty()) {
            redflags = redFlagService.search(search);
        } else {
            redflags = redFlagService.findAll(sort);
        }

        model.addAttribute("redflags", redflags);
        model.addAttribute("searchQuery", search);
        model.addAttribute("currentSort", sort);
        model.addAttribute("isEmpty", redflags.isEmpty());

        return "index";
    }

    @GetMapping("/new")
    public String newRedFlag(Model model) {
        model.addAttribute("personTypes", redFlagService.getAllPersonTypes());
        model.addAttribute("contexts", redFlagService.getAllContexts());
        return "form";
    }

    @PostMapping("/add")
    public String addRedFlag(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String personInvolved,
            @RequestParam String context,
            @RequestParam String severity,
            RedirectAttributes redirectAttributes) {
        
        RedFlag redFlag = new RedFlag(title, description, personInvolved, context, severity);
        RedFlag saved = redFlagService.save(redFlag);
        redirectAttributes.addFlashAttribute("success", "Red Flag aggiunta con successo!");
        
        return "redirect:/item/" + saved.getCode();
    }

    @GetMapping("/item/{code}")
    public String viewRedFlag(@PathVariable UUID code, Model model) {
        Optional<RedFlag> redFlag = redFlagService.findById(code);
        if (redFlag.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("redflag", redFlag.get());
        return "detail";
    }

    @PostMapping("/clear")
    public String clearCatalog(RedirectAttributes redirectAttributes) {
        redFlagService.deleteAll();
        redirectAttributes.addFlashAttribute("success", "Catalogo svuotato!");
        return "redirect:/";
    }
}