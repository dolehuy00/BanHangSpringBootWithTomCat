
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
import web.Service.StatusService;

@Controller
public class RegisterController {
    @Autowired CustomerService cusServ;
    @Autowired StatusService statusServ;
    @Autowired HttpSession session;
    
    
    @GetMapping("register")
    public String Login(){
        return "account/register";
    }
    
    
    @PostMapping("register")
    public String Login(
            @RequestParam("register-name") String name,
            @RequestParam("register-username") String username,
            @RequestParam("register-password") String password,
            @RequestParam("register-password-confirm") String passwordConfirm,
            @RequestParam("register-email") String email,
            @RequestParam("register-address") String address,
            Model model){
        if (password.length() <6) {
            model.addAttribute("messageNotLong", "Mật khẩu ít nhất 6 ký tự!");
            return "account/register";
        }else if(!password.equals(passwordConfirm)){
            model.addAttribute("messageNotMatch", "Hai mật khẩu không khớp nhau!");
            return "account/register";
        }else{
            Customer customer = new Customer();
            customer.setName(name);
            customer.setUsername(username);
            customer.setPassword(password);
            customer.setEmail(email);
            customer.setStatus(statusServ.getStatusById(1));
            if(address.length() > 0){
                customer.setAddress(address);
            }else{
                customer.setAddress("Chưa cung cấp.");
            }
            Customer newCustomer = cusServ.addNewCustomer(customer);
            session.setAttribute("CUSTOMER", newCustomer);
            return "redirect:/"; 
        }
    }
    
}
