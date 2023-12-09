
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Service.RoleService;
import web.Service.StatusService;
import web.Service.UserService;

@Controller
public class ManageManagerAccountController {
    
    @Autowired private UserService userServ;
    @Autowired private RoleService roleServ;
    @Autowired private StatusService statusServ;
    @Autowired private HttpSession session;
    
    @GetMapping("admin/manager-management")
    public String ViewAllAccount(Model model){ 
        //Kiểm tra quyền
        model.addAttribute("ListUser", userServ.findAllUserExceptAdmin());
        return "account/view-manage-manager";
    }
    
    @PostMapping("admin/manager-management/lock")
    public String LockAccount(@RequestParam("id") Integer userId){
        //Kiểm tra quyền
        userServ.lockUserById(userId);
        
        return "account/view-manage-manager";
    }
    
    @GetMapping("admin/manager-management/edit/{id}")
    public String ViewEditAccount(Model model, @PathVariable("id") Integer userID){ 
        //Kiểm tra quyền
        
        model.addAttribute("User", userServ.findUserById(userID));
        model.addAttribute("ListRole", roleServ.findAllExceptAdmin());
        model.addAttribute("ListStatus", statusServ.findAll());
        return "account/view-edit-manager";
    }
    
    @PostMapping("admin/manager-management/edit/{id}")
    public String EditAccount(Model model,
                              @PathVariable("id") Integer userID,
                              @RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("phone-number") String phoneNumber,
                              @RequestParam("address") String address,
                              @RequestParam("role") Integer roleId,
                              @RequestParam("status") Integer statusId){ 
        //Kiểm tra quyền
        
        
        
        
        
        model.addAttribute("User", userServ.findUserById(userID));
        model.addAttribute("ListRole", roleServ.findAllExceptAdmin());
        model.addAttribute("ListStatus", statusServ.findAll());
        return "account/view-edit-manager";
    }
    
    @GetMapping("admin/manager-management/add")
    public String ViewAddAccount(Model model){
        //Kiểm tra quyền
        
        
        
        
        model.addAttribute("ListRole", roleServ.findAllExceptAdmin());
        model.addAttribute("ListStatus", statusServ.findAll());
        return "account/view-add-manager";
    }
}
