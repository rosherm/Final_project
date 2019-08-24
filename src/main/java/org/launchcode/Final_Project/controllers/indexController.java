package org.launchcode.Final_Project.controllers;

import org.apache.catalina.User;
import org.launchcode.Final_Project.models.Champions;
import org.launchcode.Final_Project.models.Item;
import org.launchcode.Final_Project.models.data.championsDao;
import org.launchcode.Final_Project.models.data.itemsDao;
import org.launchcode.Final_Project.models.data.userDao;
import org.launchcode.Final_Project.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("tft")
public class indexController {

    @Autowired
    private userDao userDao;

    @Autowired
    private itemsDao itemsDao;

    @Autowired
    private championsDao championsDao;

    private Cookie userCookie;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private  HttpServletRequest request;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "TFT");
        model.addAttribute(new user());
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLogin(@ModelAttribute  @Valid user userCurrent,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "TFT");
            model.addAttribute(userCurrent);
            return "index";
        }

        String username = userCurrent.getUsername();
        String password = userCurrent.getPassword();


        if (userDao.findByUsername(username) !=null){
             user userInDb = userDao.findByUsername(username);
             if (userInDb.getPassword().equals(password)){

                 userCookie = new Cookie("username", username);
                 userCookie.setMaxAge(60*60*2);
                 userCookie.setPath("/");
                 response.addCookie(userCookie);

                 return "stats";

             }

        }
        model.addAttribute("title", "TFT");
        model.addAttribute(userCurrent);

        return "index";
    }


    @RequestMapping(value = "champions", method = RequestMethod.GET)
    public String champions(Model model){
        model.addAttribute("title", "Champions");
        model.addAttribute("Champions",championsDao.findAll());

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

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("title", "User Register");
        model.addAttribute(new user());

        return "register";

    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute  @Valid user newUser,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Register");
            return "register";
        }

        String username = newUser.getUsername();

        for (user users: userDao.findAll()){
            if(users.getUsername().equals(username)){
                model.addAttribute("title", "User Register");
                model.addAttribute(newUser);
                model.addAttribute("verifyError", "Username taken, please pick another");
                return "register";

            }
        }

        userDao.save(newUser);

        return "stats";
    }




    @RequestMapping(value = "stats", method = RequestMethod.POST)
    public String processStats(@ModelAttribute @Valid user newUser,
                                  Errors errors, Model model){

        model.addAttribute("title", "User Page");

        if (errors.hasErrors()) {
            model.addAttribute("title", "User Register");
            return "register";
        }

        return "stats";
    }

//    @RequestMapping(value = "AdminAddChampions-Items", method = RequestMethod.GET)
//    public String adminAdd(Model model){
//        model.addAttribute("title", "Admin Page");
//        model.addAttribute("Champions", new Champions());
//        model.addAttribute("Items", new Item());
//
//        return "AdminAddChampions-Items";
//    }
//
//    @RequestMapping(value = "AdminAddChampions-Items", method = RequestMethod.POST)
//    public String processAdminAdd(@ModelAttribute  @Valid Champions newChampion, @Valid Item newItems,
//                                  Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Admin Page");
//            return "AdminAddChampions-Items";
//        }
//
//        if (newChampion != null){
//
//         ///   byte[] pic = MultipartFile.newChampion.getChampionPic().getBytes();
//
//
//           championsDao.save(newChampion);
//        }
//
//        if (newItems != null){
//
//            itemsDao.save(newItems);
//
//            }
//
//        return "AdminAddChampions-Items";
//    }

}
