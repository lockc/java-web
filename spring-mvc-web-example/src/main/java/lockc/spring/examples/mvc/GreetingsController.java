package lockc.spring.examples.mvc;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingsController {
        
    @RequestMapping(value="/greetings/stuff", method=RequestMethod.POST)
    public String greeting(Model model) {
        
        model.addAttribute("stuff", Arrays.asList("Stuff 1", "Stuff 2", "Stuff 3"));
        
        /*
         * return the name of the 'view' component for Spring to render
         */
        return "greetings";
    }
    
}
