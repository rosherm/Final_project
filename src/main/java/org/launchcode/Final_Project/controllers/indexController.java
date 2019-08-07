package org.launchcode.Final_Project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
@RequestMapping("tft")
public class indexController {

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "TFT");
        return "index";
    }

    @RequestMapping(value = "champions", method = RequestMethod.GET)
    public String champions(Model model){
        model.addAttribute("title", "Champions");

        return "champions";
    }

    @RequestMapping(value = "items", method = RequestMethod.GET)
    public String items(Model model){
        model.addAttribute("title", "Items");

        return "items";
    }

    @RequestMapping(value = "stats", method = RequestMethod.GET)
    public String stats(Model model){
        model.addAttribute("title", "User Stats");

        return "stats";

    }
}
