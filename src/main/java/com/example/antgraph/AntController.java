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

    @GetMapping("/")
    public String home(){

        return "Home";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("data",new Antmodel());
        return "Login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("data",new Antmodel());
        return  "Signup";
    }


    @PostMapping("/login")
    public String loginreceive(

            @RequestParam("username") String Username,
            @RequestParam("password") String Password,
            Model model
    ){


        try {
            Antmodel data = repo.findAllByUsername(Username);
            if (Username == null) System.out.println("User Not Exist");
            else {
                if (data.getUsername().equals(Username)&&data.getPassword().equals(Password)) {
                    model.addAttribute("name",Username);
                   return "Home";
                }
            }

        }catch (Exception e){
            System.out.println(e);
            System.out.println("error oo");
        }



        model.addAttribute("data",new Antmodel());
        return "Login";
    }


    @RequestMapping("/signup")
    @ResponseBody
    public  String datasve(Antmodel antmodel){
        repo.save(antmodel);
        return "Succeess";
    }














}
