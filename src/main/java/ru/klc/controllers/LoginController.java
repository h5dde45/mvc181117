package ru.klc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import ru.klc.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute
    public User createUser() {
        return new User("UserNameValue");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(@ModelAttribute("user") User user, Locale locale) {
        System.out.println(locale.getDisplayLanguage());
        System.out.println(messageSource.getMessage("locale",
                new String[]{locale.getDisplayName(locale)}, locale));
        user.setName("UserNameValue");
        return "login";
    }


    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public ModelAndView checkUser(Locale locale, @Valid @ModelAttribute("user") User user,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            RedirectView redirectView = new RedirectView("mainpage");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            redirectAttributes.addFlashAttribute("locale", messageSource.getMessage("locale",
                    new String[]{locale.getDisplayName(locale)}, locale));
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String goMainPage(HttpServletRequest request) {
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            System.out.println("redirect...");
        } else {
            System.out.println("update...");
        }
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
