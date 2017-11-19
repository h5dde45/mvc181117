package ru.klc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.klc.objects.User;

import javax.validation.Valid;

@Controller
//@SessionAttributes("user")
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("user") User user) {
        user.setName("UserNameValue");
        return new ModelAndView("login", "user", user);
    }

//    @ModelAttribute
//    public void createUser() {
//
//    }


    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public String checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
//            , Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
//        model.addAttribute("user", user);
        return "main";
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {
        return new ModelAndView("login-failed", "message", "Login failed");
    }

    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.isAdmin());
        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
