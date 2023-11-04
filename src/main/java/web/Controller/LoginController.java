
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Customer;
import web.Service.CustomerService;


@Controller
public class LoginController {
    
    @Autowired CustomerService cusServ;
    @Autowired HttpSession session;
    
    
    @GetMapping("login")
    public String Login(){
        return "account/login";
    }

    @PostMapping("login")
    public String Login(@RequestParam("singin-username") String username,
            @RequestParam("singin-password") String password,
            Model model){  
        if (isValidAccount(username, password)) {
            session.setAttribute("CUSTOMER", cusServ.findCustomerByUserName(username));
            return "redirect:/"; 
        } else {
            model.addAttribute("messageLogin", "Sai thông tin đăng nhập");
            return "account/login";
        } 
    }
    
    //Kiểm tra thông tin đăng nhập
    private boolean isValidAccount(String username, String password) {
        Customer customer = cusServ.findCustomerByUserName(username);
        return customer != null && customer.getPassword().equals(password);
    }
}
