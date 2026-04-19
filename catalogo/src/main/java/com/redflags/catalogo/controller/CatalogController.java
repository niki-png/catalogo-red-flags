package com.redflags.catalogo.controller;

import com.redflags.catalogo.entity.RedFlag;
import com.redflags.catalogo.service.RedFlagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}