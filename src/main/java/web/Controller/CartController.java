
package web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    
    
    @GetMapping("cart/")
    public String viewCart(Model model){
        
        return "";
    }
}
