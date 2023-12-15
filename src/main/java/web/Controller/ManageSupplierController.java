
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Status;
import web.Model.Supplier;
import web.Model.User;
import web.Service.ProductService;
import web.Service.StatusService;
import web.Service.SupplierService;

@Controller
public class ManageSupplierController {
    
    @Autowired private SupplierService supServ;
    @Autowired private ProductService proServ;
    @Autowired private HttpSession session;
    @Autowired private StatusService statusServ;
    
    
    //Hiển thị danh sách nhà cung cấp
    @GetMapping("admin/supplier-management/view")
    public String ViewAllSupplier(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListSupplier", supServ.getAllSupplier());
            return "supplier/view-manage-supplier";
        }
    }
    
    //Hiển thị trang thêm nhà cung cấp
    @GetMapping("admin/supplier-management/add")
    public String ViewAddSupplier(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListStatus", statusServ.findAll());
            return "supplier/view-manage-add-supplier";
        }
    }
    
    //Nhận yêu cầu thêm nhà cung cấp
    @PostMapping("admin/supplier-management/add")
    public String AddSupplier(Model model,
            @RequestParam("name") String name,
            @RequestParam("status") Integer status){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            Supplier suplier = new Supplier();
            suplier.setName(name);
            suplier.setStatus(new Status(status));
            Supplier supplierCreated = supServ.saveSupplier(suplier);
            if (supplierCreated!=null) {
                model.addAttribute("message", "Thêm thành công");
            } else {
                model.addAttribute("name", name);
                model.addAttribute("status", status);
                model.addAttribute("message", "Thêm thất bại");
            }
            return "supplier/view-manage-add-supplier";
        }
    }
    
    //Hiển thị trang sửa nhà cung cấp
    @GetMapping("admin/supplier-management/edit/{id}")
    public String ViewEditSupplier(Model model,
            @PathVariable("id") Integer supplierId){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {  
            model.addAttribute("ListStatus", statusServ.findAll());
            model.addAttribute("Supplier", supServ.findById(supplierId));
            return "supplier/view-manage-edit-supplier";
        }
    }
    
    //Nhận yêu cầu sửa nhà cung cấp
    @PostMapping("admin/supplier-management/edit/{id}")
    public String EditSupplier(Model model,
            @PathVariable("id") Integer supplierId,
            @RequestParam("name") String name,
            @RequestParam("status") Integer status){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            try{
                Supplier suplier = supServ.findById(supplierId);
                suplier.setName(name);
                suplier.setStatus(new Status(status));
                if(status  != 1){
                    proServ.lockBySupplierId(supplierId);
                }else{
                    proServ.unlockBySupplierId(supplierId);
                }
                Supplier suplierSaved = supServ.saveSupplier(suplier);
                model.addAttribute("Supplier", suplierSaved);
                model.addAttribute("message", "Sửa thành công");
            }catch(Exception e){
                model.addAttribute("name", name);
                model.addAttribute("status", status);
                model.addAttribute("message", "Thêm thất bại");
            }
            return "supplier/view-manage-edit-supplier";
        }
    }
    
    //Nhận yêu cầu ngừng kinh doanh các sản phẩm thuộc nhà cung cấp
    @PostMapping("admin/supplier-management/lock")
    public String LockProduct(@RequestParam("id") Integer supID) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            proServ.lockBySupplierId(supID);
            supServ.lockById(supID);
            return "redirect:view";
        }
    }

    //Nhận yêu cầu tiếp tục kinh doanh các sản phẩm thuộc nhà cung cấp
    @PostMapping("admin/supplier-management/unlock")
    public String UnlockProduct(@RequestParam("id") Integer supID) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            proServ.unlockBySupplierId(supID);
            supServ.unlockById(supID);
            return "redirect:view";
        }
    }
    
    //Tìm kiếm
    @GetMapping("admin/supplier-management/search")
    public String SearchSupplier(Model model,
            @RequestParam("keyword") String keyword){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListSupplier", supServ.searchInManage(keyword));
            return "supplier/view-manage-supplier";
        }
    }
}
