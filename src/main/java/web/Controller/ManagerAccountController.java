
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.User;
import web.Service.EmailSenderService;
import web.Service.RandomService;
import web.Service.UserService;

@Controller
public class ManagerAccountController {
    
    @Autowired private UserService userServ;
    @Autowired private HttpSession session;
    @Autowired private RandomService randomServ;
    @Autowired private EmailSenderService emailServ;
    
    //Hiển thị trang đăng nhập
    @GetMapping("admin/login")
    public String ViewLogin(){  
        return "account/login-admin";
    }
    
    //Nhận yêu cầu đăng nhập
    @PostMapping("admin/login")
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model){
        User user = userServ.isValidAccount(username, password);
        if (user != null) {
            if (user.getStatus().getStatusID()!=1) {
                model.addAttribute("messageLogin", "Tài khoản này đã bị khóa");
                return "account/login-admin";
            } else {
                session.setAttribute("ADMIN", user);
                return "redirect:/admin";
            }
        } else {
            model.addAttribute("messageLogin", "Sai thông tin đăng nhập");
            return "account/login-admin";
        }
    }
    
    //Nhận yêu cầu đăng xuất
    @GetMapping("admin/logout")
    public String Logout(){
        session.removeAttribute("ADMIN");
        return "redirect:/admin";
    }
    
    //Xem trang lấy lại mật khẩu
    @GetMapping("admin/forgot")
    public String Forgot(){
        return "account/manager-forgot-password";
    }
    
    //Nhận yêu cầu lấy lại mật khẩu
    @PostMapping("admin/forgot")
    public String Forgot(@RequestParam("email") String email, Model model){
        User user = userServ.findByEmail(email);
        if(user != null){
            //Cập nhật mật khẩu
            String newPass = randomServ.generateRandomPassword(10);
            user.setPassword(newPass);
            userServ.saveUser(user);
            //Gửi mail
            String bodyEmail = "Mật khẩu mới của bạn là: "+newPass;
            String subject = "Yêu cầu lấy lại mật khẩu";
            emailServ.sendMail(user.getEmail(), subject, bodyEmail);
            //Thông báo
            model.addAttribute("message", "Email đã được gửi,"
                    + " vui lòng kiểm tra hộp thư của bạn!");
        }else{
            model.addAttribute("message", "Email này chưa được đăng ký!");
        }
        return "account/manager-forgot-password";
    }
}
