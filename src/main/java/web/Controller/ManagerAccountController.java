
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.User;
import web.Service.UserService;

@Controller
public class ManagerAccountController {
    @Autowired private UserService userServ;
    @Autowired private HttpSession session;
    
    @GetMapping("admin/login")
    public String ViewLogin(){  
        return "account/login-admin";
    }
    
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
}
