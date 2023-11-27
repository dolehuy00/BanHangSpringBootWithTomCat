
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
import web.Service.EmailSenderService;
import web.Service.StatusService;
import web.Service.SupplierService;

@Controller
public class CustomerAccountController {
    @Autowired CustomerService cusServ;
    @Autowired StatusService statusServ;
    @Autowired HttpSession session;
    @Autowired EmailSenderService emailServ;
    @Autowired private SupplierService supplierServ;
    
    @GetMapping("register")
    public String Register(Model model){
        model.addAttribute("listSupplier", supplierServ.getAllSupplier());
        return "account/register";
    }
    
    
    @PostMapping("register")
    public String Register(
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
        }else if(cusServ.checkExitsAccoutByEmail(email)){
            model.addAttribute("messageUsedEmail", "Email đã được sử dụng, vui lòng nhập email khác!");
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
    
    @GetMapping("login")
    public String Login(Model model){
        model.addAttribute("listSupplier", supplierServ.getAllSupplier());
        return "account/login";
    }

    @PostMapping("login")
    public String Login(@RequestParam("singin-username") String username,
            @RequestParam("singin-password") String password,
            Model model){  
        if (cusServ.isValidAccount(username, password)) {
            Customer customer = cusServ.findCustomerByUserName(username);
            customer.setPassword("");
            session.setAttribute("CUSTOMER", customer);
            return "redirect:/"; 
        } else {
            model.addAttribute("messageLogin", "Sai thông tin đăng nhập");
            return "account/login";
        } 
    }
    
    @GetMapping("forgot")
    public String Forgot(){
        return "account/customer-forgot-password";
    }
    
    @PostMapping("forgot")
    public String Forgot(@RequestParam("email") String email, Model model){
        Customer customer = cusServ.findCustomerByEmail(email);
        if(customer != null){
            //Cập nhật mật khẩu
            String newPass = cusServ.generateRandomPassword(6);
            customer.setPassword(newPass);
            cusServ.updateCustomer(customer);
            //Gửi mail
            String bodyEmail = "Mật khẩu mới của bạn là: "+newPass;
            String subject = "Yêu cầu lấy lại mật khẩu";
            emailServ.sendMail(customer.getEmail(), subject, bodyEmail);
            //Thông báo
            model.addAttribute("message", "Email đã được gửi, vui lòng kiểm tra hộp thư của bạn!");
        }else{
            model.addAttribute("message", "Email này chưa được đăng ký!");
        }
        return "account/customer-forgot-password";
    }
}
