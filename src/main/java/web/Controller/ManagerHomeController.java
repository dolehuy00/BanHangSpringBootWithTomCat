
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.UserService;

@Controller
public class ManagerHomeController {
    @Autowired private UserService userServ;
    
    @GetMapping("admin")
    public String ViewIndex(){
        
       return "index/index-admin";
    }
}
