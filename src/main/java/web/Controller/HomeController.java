
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.UserService;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
   
    @GetMapping("/")
    public String Index(Model model){
        
        model.addAttribute("items", userServ.findAllUser());
        
        return "index";
    }
}
