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
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


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

                 model.addAttribute("Games",gamesByUserId());
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

    public List<Game> gamesByUserId() {
        Cookie[] cookies = request.getCookies();
        ArrayList<Game> games = new ArrayList<Game>();

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("username")) {
                user cookieuser = userDao.findByUsername(cookie.getValue());
                for (Game g : gamesDao.findAll()) {
                    if (g.getUser() != null) {
                        int gameUserId = g.getUser().getId();
                        int cookieUserId = cookieuser.getId();
                        if (gameUserId == cookieUserId) {
                            games.add(g);
                        }
                    }
                }
            }
        }
        return games;
    }



    @RequestMapping(value = "stats", method = RequestMethod.GET)
    public String stats(Model model){
        model.addAttribute("title", "User Stats");
        model.addAttribute("Games",gamesByUserId());





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

    public void makegameChampion(int champ, Game currentGame, int Item, int Item2, int Item3){
        if (champ != 0) {
            Champions newChampion = championsDao.findOne(champ);

            GameChampion newgameChampion = new GameChampion(newChampion, currentGame);

            gamechampionDao.save(newgameChampion);

            if (Item != 0){

                Item newItem = itemsDao.findOne(Item);

                GameChampionItem newgameChampionItem = new GameChampionItem(newItem, newgameChampion);

                gamechampionitemDao.save(newgameChampionItem);
            }
            if (Item2 != 0){

                Item newItem = itemsDao.findOne(Item2);

                GameChampionItem newgameChampionItem = new GameChampionItem(newItem, newgameChampion);

                gamechampionitemDao.save(newgameChampionItem);
            }
            if (Item3 != 0){

                Item newItem = itemsDao.findOne(Item3);

                GameChampionItem newgameChampionItem = new GameChampionItem(newItem, newgameChampion);

                gamechampionitemDao.save(newgameChampionItem);
            }

        }
    }

    public void makegameItem(Game currentGame, int Item){
        if (Item != 0) {
            Item newItem = itemsDao.findOne(Item);

            GameItem newgameItem = new GameItem(newItem, currentGame);

            gameitemDao.save(newgameItem);
        }
    }




    @RequestMapping(value = "gameEntry", method = RequestMethod.POST)
    public String processgameEntry(@ModelAttribute  @Valid Game newGame, @RequestParam(value = "Champions", required = false) int Champions,
                                   @RequestParam(value = "Champions2", required = false) int Champions2,
                                   @RequestParam(value = "Champions3", required = false) int Champions3,
                                   @RequestParam(value = "Champions4", required = false) int Champions4,
                                   @RequestParam(value = "Champions5", required = false) int Champions5,
                                   @RequestParam(value = "Champions6", required = false) int Champions6,
                                   @RequestParam(value = "Champions7", required = false) int Champions7,
                                   @RequestParam(value = "Champions8", required = false) int Champions8,
                                   @RequestParam(value = "Champions9", required = false) int Champions9,
                                   @RequestParam(value = "Champions10", required = false) int Champions10,
                                   @RequestParam(value = "Items", required = false) int Items,
                                   @RequestParam(value = "Items2", required = false) int Items2,
                                   @RequestParam(value = "Items3", required = false) int Items3,
                                   @RequestParam(value = "Items4", required = false) int Items4,
                                   @RequestParam(value = "Items5", required = false) int Items5,
                                   @RequestParam(value = "Items6", required = false) int Items6,
                                   @RequestParam(value = "Items7", required = false) int Items7,
                                   @RequestParam(value = "Items8", required = false) int Items8,
                                   @RequestParam(value = "Items9", required = false) int Items9,
                                   @RequestParam(value = "Items10", required = false) int Items10,
                                   @RequestParam(value = "Items11", required = false) int Items11,
                                   @RequestParam(value = "Items12", required = false) int Items12,
                                   @RequestParam(value = "Items13", required = false) int Items13,
                                   @RequestParam(value = "Items14", required = false) int Items14,
                                   @RequestParam(value = "Items15", required = false) int Items15,
                                   @RequestParam(value = "Items16", required = false) int Items16,
                                   @RequestParam(value = "Items17", required = false) int Items17,
                                   @RequestParam(value = "Items18", required = false) int Items18,
                                   @RequestParam(value = "Items19", required = false) int Items19,
                                   @RequestParam(value = "Items20", required = false) int Items20,
                                   @RequestParam(value = "Items21", required = false) int Items21,
                                   @RequestParam(value = "Items22", required = false) int Items22,
                                   @RequestParam(value = "Items23", required = false) int Items23,
                                   @RequestParam(value = "Items24", required = false) int Items24,
                                   @RequestParam(value = "Items25", required = false) int Items25,
                                   @RequestParam(value = "Items26", required = false) int Items26,
                                   @RequestParam(value = "Items27", required = false) int Items27,
                                   @RequestParam(value = "Items28", required = false) int Items28,
                                   @RequestParam(value = "Items29", required = false) int Items29,
                                   @RequestParam(value = "Items30", required = false) int Items30,
                                   @RequestParam(value = "Items31", required = false) int Items31,
                                   @RequestParam(value = "Items32", required = false) int Items32,
                                   @RequestParam(value = "Items33", required = false) int Items33,
                                   @RequestParam(value = "Items34", required = false) int Items34,
                                   @RequestParam(value = "Items35", required = false) int Items35,
                                   @RequestParam(value = "Items36", required = false) int Items36,
                                   @RequestParam(value = "Items37", required = false) int Items37,
                                   @RequestParam(value = "Items38", required = false) int Items38,
                                   @RequestParam(value = "Items39", required = false) int Items39,
                                   @RequestParam(value = "Items40", required = false) int Items40,
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

                makegameChampion(Champions,newGame,Items,Items11,Items21);

                makegameChampion(Champions2,newGame,Items2,Items12,Items22);

                makegameChampion(Champions3,newGame,Items3,Items13,Items23);

                makegameChampion(Champions4,newGame,Items4,Items14,Items24);

                makegameChampion(Champions5,newGame,Items5,Items15,Items25);

                makegameChampion(Champions6,newGame,Items6,Items16,Items26);

                makegameChampion(Champions7,newGame,Items7,Items17,Items27);

                makegameChampion(Champions8,newGame,Items8,Items18,Items28);

                makegameChampion(Champions9,newGame,Items9,Items19,Items29);

                makegameChampion(Champions10,newGame,Items10,Items20,Items30);

                makegameItem(newGame, Items31);

                makegameItem(newGame, Items32);

                makegameItem(newGame, Items33);

                makegameItem(newGame, Items34);

                makegameItem(newGame, Items35);

                makegameItem(newGame, Items36);

                makegameItem(newGame, Items37);

                makegameItem(newGame, Items38);

                makegameItem(newGame, Items39);

                makegameItem(newGame, Items40);


                model.addAttribute("Games", gamesByUserId());
                model.addAttribute("title", "Stats");

                return "redirect:stats";
            }
        }

        model.addAttribute("Games",gamesByUserId());
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
