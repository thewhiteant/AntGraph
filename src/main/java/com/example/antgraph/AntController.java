package com.example.antgraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class AntController {

    @Autowired
    ControlDB repo;


    //Home section


    @GetMapping("/")
    public String home() {
        return "Home";
    }


    //Login section


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("data", new Antmodel());
        return "Login";
    }


    @PostMapping("/login")
    public String loginreceive(

            @RequestParam("username") String Username,
            @RequestParam("password") String Password,
            Model model
    ) {


        try {
            Antmodel data = repo.findAllByUsername(Username);
            if (Username == null) System.out.println("User Not Exist");
            else {
                if (data.getUsername().equals(Username) && data.getPassword().equals(Password)) {
                    model.addAttribute("name", Username);
                    return "Home";
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error oo");
        }


        model.addAttribute("data", new Antmodel());
        return "Login";
    }


    //SighnUp section

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("data", new Antmodel());
        return "Signup";
    }


    @RequestMapping("/signup")
    @ResponseBody
    public String datasve(@RequestParam("username") String Username, Antmodel antmodel) {
//    TODO: Empty submission accepted fix

    Antmodel usr = repo.findAllByUsername(Username);
    if (usr != null) return "User Already Axist";
    antmodel.setGraphId(repo.findAll().size()+1);
    repo.save(antmodel);
    return "Succeess";
    }





}