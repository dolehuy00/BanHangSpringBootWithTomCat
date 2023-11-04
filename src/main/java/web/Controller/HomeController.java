
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.ProductService;


@Controller
public class HomeController {
    
    @Autowired private ProductService prodServ;
    
    @GetMapping("/")
    public String Index(Model model){
        
        return "index/index";
    }
}
