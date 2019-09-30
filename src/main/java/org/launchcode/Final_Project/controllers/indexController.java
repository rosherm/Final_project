package org.launchcode.Final_Project.controllers;

import org.apache.catalina.User;
import org.launchcode.Final_Project.models.*;
import org.launchcode.Final_Project.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("tft")
public class indexController {

    @Autowired
    private attributeDao attributeDao;

    @Autowired
    private bonusDao bonusDao;

    @Autowired
    private gamechampionDao gamechampionDao;

    @Autowired
    private gamechampionitemDao gamechampionitemDao;

    @Autowired
    private gameitemDao gameitemDao;

    @Autowired
    private userDao userDao;

    @Autowired
    private gamesDao gamesDao;

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

                 model.addAttribute("Games",gamesDao.findAll());
                 model.addAttribute("title", "Stats");

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
        model.addAttribute("Item", itemsDao.findAll());

        return "items";
    }

    @RequestMapping(value = "stats", method = RequestMethod.GET)
    public String stats(Model model){
        model.addAttribute("title", "User Stats");
        model.addAttribute("Games",gamesDao.findAll());

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

        userCookie = new Cookie("username", username);
        userCookie.setMaxAge(60*60*2);
        userCookie.setPath("/");
        response.addCookie(userCookie);

        model.addAttribute("title", "User Stats");

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


    @RequestMapping(value = "gameEntry", method = RequestMethod.GET)
    public String gameEntry(Model model){
        model.addAttribute("title", "Game Entry");
        model.addAttribute("Champions", championsDao.findAll());
        model.addAttribute("Item", itemsDao.findAll());
        model.addAttribute("Game",new Game());
        model.addAttribute("GameChampion", new GameChampion());
        model.addAttribute("GameChampionItem", new GameChampionItem());
        model.addAttribute("GameItem", new GameItem());

        return "gameEntry";

    }

    @RequestMapping(value = "gameEntry", method = RequestMethod.POST)
    public String processgameEntry(@ModelAttribute  @Valid Game newGame, @RequestParam(value = "Champions", required = false, defaultValue = "") int Champions,
                                   @RequestParam(value = "Champions2", required = false, defaultValue = "") int Champions2,
                                   @RequestParam(value = "Champions3", required = false, defaultValue = "") int Champions3,
                                   @RequestParam(value = "Champions4", required = false, defaultValue = "") int Champions4,
                                   @RequestParam(value = "Champions5", required = false, defaultValue = "") int Champions5,
                                   @RequestParam(value = "Champions6", required = false, defaultValue = "") int Champions6,
                                   @RequestParam(value = "Champions7", required = false, defaultValue = "") int Champions7,
                                   @RequestParam(value = "Champions8", required = false, defaultValue = "") int Champions8,
                                   @RequestParam(value = "Champions9", required = false, defaultValue = "") int Champions9,
                                   @RequestParam(value = "Champions10", required = false, defaultValue = "") int Champions10,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Game Entry");
            model.addAttribute("Champions", championsDao.findAll());
            model.addAttribute("Item", itemsDao.findAll());
            model.addAttribute("Game",newGame);
            model.addAttribute("GameChampion", new GameChampion());
            model.addAttribute("GameChampionItem", new GameChampionItem());
            model.addAttribute("GameItem", new GameItem());


            return "gameEntry";
        }

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies){
            if(cookie.getName().equals("username")){
                user cookieuser = userDao.findByUsername(cookie.getValue());

                newGame.setUser(cookieuser);

                cookieuser.addGames(newGame);

                gamesDao.save(newGame);

                //Gets the fist selected champion
                Champions newChampion = championsDao.findOne(Champions);

                GameChampion newgameChampion = new GameChampion(newChampion, newGame);

                gamechampionDao.save(newgameChampion);

                //Gets the Second selected champion
                Champions newChampion2 = championsDao.findOne(Champions2);

                GameChampion newgameChampion2 = new GameChampion(newChampion2, newGame);

                gamechampionDao.save(newgameChampion2);

                //Gets the Third selected champion
                Champions newChampion3 = championsDao.findOne(Champions3);

                GameChampion newgameChampion3 = new GameChampion(newChampion3, newGame);

                gamechampionDao.save(newgameChampion3);

                //Gets the Fourth selected champion
                Champions newChampion4 = championsDao.findOne(Champions4);

                GameChampion newgameChampion4 = new GameChampion(newChampion4, newGame);

                gamechampionDao.save(newgameChampion4);

                //Gets the Fifth selected champion
                Champions newChampion5 = championsDao.findOne(Champions5);

                GameChampion newgameChampion5 = new GameChampion(newChampion5, newGame);

                gamechampionDao.save(newgameChampion5);

                //Gets the Sixth selected champion
                Champions newChampion6 = championsDao.findOne(Champions6);

                GameChampion newgameChampion6 = new GameChampion(newChampion6, newGame);

                gamechampionDao.save(newgameChampion6);

                //Gets the Seventh selected champion
                Champions newChampion7 = championsDao.findOne(Champions7);

                GameChampion newgameChampion7 = new GameChampion(newChampion7, newGame);

                gamechampionDao.save(newgameChampion7);

                //Gets the eight selected champion
                Champions newChampion8 = championsDao.findOne(Champions8);

                GameChampion newgameChampion8 = new GameChampion(newChampion8, newGame);

                gamechampionDao.save(newgameChampion8);

                //Gets the Ninth selected champion
                Champions newChampion9 = championsDao.findOne(Champions9);

                GameChampion newgameChampion9 = new GameChampion(newChampion9, newGame);

                gamechampionDao.save(newgameChampion9);

                //Gets the Tenth selected champion
                Champions newChampion10 = championsDao.findOne(Champions10);

                GameChampion newgameChampion10 = new GameChampion(newChampion10, newGame);

                gamechampionDao.save(newgameChampion10);



                model.addAttribute("Games",gamesDao.findAll());

                model.addAttribute("title", "Stats");

                return "stats";
            }
        }

        model.addAttribute("Games",gamesDao.findAll());
        model.addAttribute("title", "Stats");

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
