
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
import web.DTO.CustomerDTO;
import web.Model.Customer;
import web.Model.Status;
import web.Model.User;
import web.Service.CartService;
import web.Service.CustomerService;
import web.Service.StatusService;

@Controller
public class ManageCustomerAccountController {
    
    @Autowired private CustomerService customerServ;
    @Autowired private StatusService statusServ;
    @Autowired private HttpSession session;
    @Autowired private CartService cartServ;
    
    //Hiển thị trang quản lý khách hàng
    @GetMapping("admin/customer-management/view")
    public String ViewAllAccount(Model model){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListCustomer", customerServ.findAll());
            return "account/view-manage-customer";
        }
    }
    
    //Nhận yêu cầu khóa tài khoản khách hàng
    @PostMapping("admin/customer-management/lock")
    public String LockAccount(@RequestParam("id") Integer Id){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            customerServ.lockById(Id);
            return "redirect:view";
        }
    }
    
    //Nhận yêu cầu mở khóa tài khoản khách hàng
    @PostMapping("admin/customer-management/unlock")
    public String UnlockAccount(@RequestParam("id") Integer Id){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            customerServ.unlockById(Id);
            return "redirect:view";
        }
    }
    
    //Hiển thị trang sửa khách hàng
    @GetMapping("admin/customer-management/edit/{id}")
    public String ViewEditAccount(Model model,
            @PathVariable("id") Integer customerId){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("Customer", customerServ.findById(customerId));
            model.addAttribute("ListStatus", statusServ.findAll());
            return "account/view-edit-customer";
        }
    }
    
    //Nhận yêu cầu sửa khách hàng
    @PostMapping("admin/customer-management/edit/{id}")
    @ResponseBody
    public String EditAccount(Model model,
            @PathVariable("id") Integer customerId,
            @RequestBody() CustomerDTO newCusDTO){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID() != 1){
            return "account/view-role-not-permission";
        }else{
            JSONObject response = new JSONObject();
            boolean isValid = true;
            Customer oldCustomer = customerServ.findById(customerId);
            String email = newCusDTO.getEmail();
            if(!email.equals(oldCustomer.getEmail())
                    && customerServ.checkExitsAccoutByEmail(email)){
                response.put("ExitsEmail", true);
                isValid = false;
            }
            if(newCusDTO.getStatus() == null){
                response.put("ErrorStatus", true);
                isValid = false;
            }
            if (!isValid) {
                return response.toString();
            } else { 
                oldCustomer.setName(newCusDTO.getName());
                oldCustomer.setEmail(email);
                oldCustomer.setPhoneNumber(newCusDTO.getPhoneNumber());
                oldCustomer.setAddress(newCusDTO.getAddress());
                oldCustomer.setStatus(new Status(newCusDTO.getStatus()));
                customerServ.saveCustomer(oldCustomer);
                response.put("Success", true);
                return response.toString();
            }
        }
    }
    
    //Nhận yêu cầu thêm khách hàng
    @PostMapping("admin/customer-management/add")
    @ResponseBody
    public String AddAccount(Model model,
            @RequestBody() CustomerDTO newCusDTO){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            JSONObject response = new JSONObject();
            boolean isValid = true;
            if (customerServ.checkExitsAccoutByUsername(newCusDTO.getUsername())) {
                response.put("ExitsUsername", true);
                isValid = false;
            }
            if (newCusDTO.getPassword().length() <6) {
                response.put("PasswordNotLong", true);
                isValid = false;
            }
            if(!newCusDTO.getPassword().equals(newCusDTO.getPasswordConfirm())){
                response.put("PasswordNotEquals", true);
                isValid = false;
            }
            if(customerServ.checkExitsAccoutByEmail(newCusDTO.getEmail())){
                response.put("ExitsEmail", true);
                isValid = false;
            }
            if(newCusDTO.getStatus()==null){
                response.put("ErrorStatus", true);
                isValid = false;
            }
            if (!isValid) {
                return response.toString();
            } else {
                Customer newCustomer =  new Customer();
                newCustomer.setName(newCusDTO.getName());
                newCustomer.setUsername(newCusDTO.getUsername());
                newCustomer.setPassword(newCusDTO.getPassword());
                newCustomer.setEmail(newCusDTO.getEmail());
                newCustomer.setPhoneNumber(newCusDTO.getPhoneNumber());
                newCustomer.setAddress(newCusDTO.getAddress());
                newCustomer.setStatus(new Status(newCusDTO.getStatus()));
                Customer customerCreated = customerServ.saveCustomer(newCustomer);
                cartServ.createEmptyCartForCustomer(customerCreated);
                response.put("Success", true);
                return response.toString();
            }
        }
    }
    
    //Hiển thị trang thêm khách hàng
    @GetMapping("admin/customer-management/add")
    public String ViewAddAccount(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListStatus", statusServ.findAll());
            return "account/view-add-customer";
        }
    }
    //Tìm kiếm
    @GetMapping("admin/customer-management/search")
    public String SearchAccount(Model model,
            @RequestParam("keyword") String keyword){ 
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListCustomer", customerServ.searchInManage(keyword));
            return "account/view-manage-customer";
        }
    }
}
