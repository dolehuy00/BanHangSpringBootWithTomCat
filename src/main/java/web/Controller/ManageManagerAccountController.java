
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.DTO.UserDTO;
import web.Model.Role;
import web.Model.Status;
import web.Model.User;
import web.Service.RoleService;
import web.Service.StatusService;
import web.Service.UserService;

@Controller
public class ManageManagerAccountController {
    
    @Autowired private UserService userServ;
    @Autowired private RoleService roleServ;
    @Autowired private StatusService statusServ;
    @Autowired private HttpSession session;
    
    //Hiển thị trang quản lý nhân viên
    @GetMapping("admin/manager-management/view")
    public String ViewAllAccount(Model model){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListUser", userServ.findAllUserExceptAdmin());
            return "account/view-manage-manager";
        }
    }
    
    //Nhận yêu cầu khóa tài khoản nhân viên
    @PostMapping("admin/manager-management/lock")
    public String LockAccount(@RequestParam("id") Integer userId){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            userServ.lockUserById(userId);
            return "redirect:view";
        }
    }
    
    //Nhận yêu cầu mở khóa tài khoản nhân viên
    @PostMapping("admin/manager-management/unlock")
    public String UnlockAccount(@RequestParam("id") Integer userId){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            userServ.unlockUserById(userId);
            return "redirect:view";
        }
    }
    
    //Hiển thị trang sửa nhân viên
    @GetMapping("admin/manager-management/edit/{id}")
    public String ViewEditAccount(Model model,
            @PathVariable("id") Integer userID){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("User", userServ.findUserById(userID));
            model.addAttribute("ListRole", roleServ.findAllExceptAdmin());
            model.addAttribute("ListStatus", statusServ.findAll());
            return "account/view-edit-manager";
        }
    }
    
    //Nhận yêu cầu sửa nhân viên
    @PostMapping("admin/manager-management/edit/{id}")
    @ResponseBody
    public String EditAccount(Model model,
            @PathVariable("id") Integer userID,
            @RequestBody() UserDTO newUserDTO){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID() != 1){
            return "account/view-role-not-permission";
        }else{
            JSONObject response = new JSONObject();
            boolean isValid = true;
            User oldUser = userServ.findUserById(userID);
            String email = newUserDTO.getEmail();
            String phoneNumber = newUserDTO.getPhoneNumber();
            if(!email.equals(oldUser.getEmail())
                    && userServ.checkExitsAccoutByEmail(email)){
                response.put("ExitsEmail", true);
                isValid = false;
            }
            if(!phoneNumber.equals(oldUser.getPhoneNumber())
                    && userServ.checkExitsAccoutByPhoneNumber(phoneNumber)){
                response.put("ExitsPhoneNumber", true);
                isValid = false;
            }
            if(newUserDTO.getRole() == null){
                response.put("ErrorRole", true);
                isValid = false;
            }
            if(newUserDTO.getStatus() == null){
                response.put("ErrorStatus", true);
                isValid = false;
            }
            if (!isValid) {
                return response.toString();
            } else { 
                oldUser.setName(newUserDTO.getName());
                oldUser.setEmail(email);
                oldUser.setPhoneNumber(phoneNumber);
                oldUser.setAddress(newUserDTO.getAddress());
                oldUser.setRole(new Role(newUserDTO.getRole()));
                oldUser.setStatus(new Status(newUserDTO.getStatus()));
                userServ.saveUser(oldUser);
                response.put("Success", true);
                return response.toString();
            }
        }
    }
    
    //Nhận yêu cầu thêm nhân viên
    @PostMapping("admin/manager-management/add")
    @ResponseBody
    public String AddAccount(Model model, @RequestBody() UserDTO newUserDTO){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            JSONObject response = new JSONObject();
            boolean isValid = true;
            if (userServ.checkExitsAccoutByUsername(newUserDTO.getUsername())) {
                response.put("ExitsUsername", true);
                isValid = false;
            }
            if (newUserDTO.getPassword().length() <6) {
                response.put("PasswordNotLong", true);
                isValid = false;
            }
            if(!newUserDTO.getPassword().equals(newUserDTO.getPasswordConfirm())){
                response.put("PasswordNotEquals", true);
                isValid = false;
            }
            if(userServ.checkExitsAccoutByEmail(newUserDTO.getEmail())){
                response.put("ExitsEmail", true);
                isValid = false;
            }
            if(userServ.checkExitsAccoutByPhoneNumber(newUserDTO.getPhoneNumber())){
                response.put("ExitsPhoneNumber", true);
                isValid = false;
            }
            if(newUserDTO.getRole()==null){
                response.put("ErrorRole", true);
                isValid = false;
            }
            if(newUserDTO.getStatus()==null){
                response.put("ErrorStatus", true);
                isValid = false;
            }
            if (!isValid) {
                return response.toString();
            } else {
                User newUser =  new User();
                newUser.setName(newUserDTO.getName());
                newUser.setUsername(newUserDTO.getUsername());
                newUser.setPassword(newUserDTO.getPassword());
                newUser.setEmail(newUserDTO.getEmail());
                newUser.setPhoneNumber(newUserDTO.getPhoneNumber());
                newUser.setAddress(newUserDTO.getAddress());
                newUser.setRole(new Role(newUserDTO.getRole()));
                newUser.setStatus(new Status(newUserDTO.getStatus()));
                userServ.saveUser(newUser);
                response.put("Success", true);
                return response.toString();
            }
        }
    }
    
    //Hiển thị trang thêm nhân viên
    @GetMapping("admin/manager-management/add")
    public String ViewAddAccount(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListRole", roleServ.findAllExceptAdmin());
            model.addAttribute("ListStatus", statusServ.findAll());
            return "account/view-add-manager";
        }
    }
 
    //Tìm kiếm
    @GetMapping("admin/manager-management/search")
    public String SearchAccount(Model model,
            @RequestParam("keyword") String keyword){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
             model.addAttribute("ListUser", userServ.searchInManage(keyword));
            return "account/view-manage-manager";
        }
    }
}
