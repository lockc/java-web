package lockc.spring.examples.mvc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginPage(Model model) {
    
        model.addAttribute("userlogin", new UserLogin());
        
        /*
         * return the name of the 'view' component for Spring to render
         */
        return "login";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute UserLogin userlogin, Model model) {
        
        model.addAttribute("username", userlogin.getUsername());
        model.addAttribute("userlogin", userlogin);
        model.addAttribute("stuff", Arrays.asList("Stuff 1", "Stuff 2", "Stuff 3"));
        
        /*
         * return the name of the 'view' component for Spring to render
         */
        return "greetings";
    }
    
}
