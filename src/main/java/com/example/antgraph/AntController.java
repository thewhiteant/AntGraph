package com.example.antgraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
                    MainGraphControl test = new MainGraphControl(repo.findAll().size());
                    test.LoadGraph();
//                    model.addAttribute("item",test.BFS(data.getGraphId()));
                    List<String> items = new ArrayList<>();
                    for (int i:test.BFS(data.getGraphId()))
                    {
                       Antmodel usr = repo.findByGraphId(i);
                       items.add(usr.getUsername());
                    }

                    System.out.println(items);
                    model.addAttribute("items", items);

                    return "Home";
                }
            }
//
        } catch (Exception e) {
            System.out.println(e);

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



    //Extra

    @GetMapping("/friendadd")
    public String grapadd(){
        return "AddFreinds";
    }

    @PostMapping("/friendadd")
    @ResponseBody
    public String Addgraph(
            @RequestParam("fusr") String firstUser,
            @RequestParam("sndusr") String SecondUser
    ){
        MainGraphControl grap = new MainGraphControl(repo.findAll().size());
        grap.LoadGraph();

        Antmodel usr1 = repo.findAllByUsername(firstUser);
        Antmodel usr2 = repo.findAllByUsername(SecondUser);
        grap.Joint(usr1.getGraphId(),usr2.getGraphId());
        return "Added";
    }

}



