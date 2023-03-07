package tz.go.bot.controller;

import org.springframework.web.bind.annotation.*;
import tz.go.bot.model.Hello;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "Hello from Bank of Tanzania";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Bank of Tanzania";
    }

    @GetMapping("/names")
    public List<String> getCandidateNames(){
        List<String> names=new ArrayList<>();
        names.add("Tom");
        names.add("Alex");
        names.add("Mike");
        names.add("John");

        return  names;
    }

    @GetMapping("/hellos")
    public Hello getHello(){
        Hello h=new Hello();
        h.setName("Tom");
        h.setCity("Mumbai");
        return  h;
    }

    @GetMapping("/users/{id}")
    public String testPathVariable(@PathVariable(name = "id") int id){
        String message="User at id #"+id+" is Tom";
        return  message;
    }
    @GetMapping("/orders")
    public String testRequestParameter(@RequestParam(name = "id") int id){
        String message="Order at id #"+id+" is DELIVERED";
        return  message;
    }
}
