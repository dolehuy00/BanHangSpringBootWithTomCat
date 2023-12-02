
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.Customer;
import web.Model.Orders;
import web.Service.CartService;
import web.Service.CustomerService;
import web.Service.EmailSenderService;
import web.Service.OrderService;
import web.Service.StatusService;

@Controller
public class CustomerAccountController {
    @Autowired private CustomerService cusServ;
    @Autowired private StatusService statusServ;
    @Autowired private HttpSession session;
    @Autowired private EmailSenderService emailServ;
    @Autowired private OrderService orderServ;
    @Autowired private CartService cartServ;
    
    //Xem trang đăng ký
    @GetMapping("register")
    public String Register(){
        return "account/register";
    }
    
    //Nhận yêu cầu đăng ký
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
                customer.setAddress("Chưa cung cấp");
            }
            Customer newCustomer = cusServ.addNewCustomer(customer);
            session.setAttribute("CUSTOMER", newCustomer);
            return "redirect:/login"; 
        }
    }
    
    //Xem trang đăng nhập
    @GetMapping("login")
    public String Login(){
        return "account/login";
    }

    //Nhận yêu cầu đăng nhập
    @PostMapping("login")
    public String Login(@RequestParam("singin-username") String username,
            @RequestParam("singin-password") String password,
            Model model){
        if (cusServ.isValidAccount(username, password)) {
            Customer customer = cusServ.findCustomerByUserName(username);
            if (customer.getStatus().getStatusID()!=1) {
                model.addAttribute("messageLogin", "Tài khoản này đã bị khóa");
                return "account/login";
            } else {
                session.setAttribute("CUSTOMER", customer);
                return "redirect:/"; 
            }
        } else {
            model.addAttribute("messageLogin", "Sai thông tin đăng nhập");
            return "account/login";
        } 
    }
    
    //Nhận yêu cầu đăng xuất
    @GetMapping("logout")
    public String Logout(){
        session.removeAttribute("CUSTOMER");
        return "redirect:/";
    }
    
    //Xem trang lấy lại mật khẩu
    @GetMapping("forgot")
    public String Forgot(){
        return "account/customer-forgot-password";
    }
    
    //Nhận yêu cầu lấy lại mật khẩu
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
            model.addAttribute("message", "Email đã được gửi,"
                    + " vui lòng kiểm tra hộp thư của bạn!");
        }else{
            model.addAttribute("message", "Email này chưa được đăng ký!");
        }
        return "account/customer-forgot-password";
    }
    
    //Xem thông tin cá nhân
    @GetMapping("profile")
    public String Profile(Model model){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        List<Orders> order = orderServ.findOrdersByCustomer(customer);
        model.addAttribute("ListOrder", order);
        return "account/account-customer-info";
    }
    
    //Nhận yêu cầu đổi mật khẩu
    @PostMapping("profile/change-pass")
    @ResponseBody
    public String ChangePass(Model model,
            @RequestParam("old-pass")String oldPass,
            @RequestParam("new-pass")String newPass,
            @RequestParam("confirm-pass")String confirmPass){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(!customer.getPassword().equals(oldPass)){
            JSONObject responseData = new JSONObject();
            responseData.put("MessageOldPassNotMatch","Mật khẩu hiện tại không đúng!");
            return responseData.toString();
        }
        else if (newPass.length() < 6) {
            JSONObject responseData = new JSONObject();
            responseData.put("MessageNotLong","Mật khẩu ít nhất 6 ký tự!");
            return responseData.toString();
        }else if(!newPass.equals(confirmPass)){
            JSONObject responseData = new JSONObject();
            responseData.put("MessageNotMatch","Hai mật khẩu không khớp nhau!");
            return responseData.toString();
        }else{
            customer.setPassword(newPass);
            Customer newCustomer = cusServ.updateCustomer(customer);
            session.setAttribute("CUSTOMER", newCustomer);
            JSONObject responseData = new JSONObject();
            responseData.put("MessageChangePass","Đã đổi thành công!");
            return responseData.toString();
        }
    }
    
    //Nhận yêu cầu chỉnh sửa thông tin cá nhân
    @PostMapping("profile/change-info")
    public String ChangeInfo(Model model,
            @RequestParam("name")String name,
            @RequestParam("email")String email,
            @RequestParam("address")String address){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(!email.equals(customer.getEmail()) && cusServ.checkExitsAccoutByEmail(email)){
            model.addAttribute("messageUsedEmail", "Email đã được sử dụng, vui lòng nhập email khác!");
            return "account/account-customer-info";
        }
        customer.setName(name);
        customer.setEmail(email);
        if(address.length() > 0){
            customer.setAddress(address);
        }else{
            customer.setAddress("Chưa cung cấp");
        }
        Customer newCustomer = cusServ.updateCustomer(customer);
        session.setAttribute("CUSTOMER", newCustomer);
        return "redirect:/profile";
    }
}
