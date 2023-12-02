
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Model.Customer;
import web.Service.CartService;

@Controller
public class CartController {
    
    @Autowired private CartService cartServ;    
    @Autowired HttpSession session;
    
    @GetMapping("cart")
    public String viewCart(Model model){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            model.addAttribute("cart", cartServ.getCartByCustomer(customer));
            return "cart/view-cart";
        }
        return "account/login";
    }
}
