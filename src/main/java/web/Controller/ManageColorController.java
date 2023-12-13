
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Color;
import web.Model.User;
import web.Service.ColorService;

@Controller
public class ManageColorController {
    
    @Autowired private ColorService colorServ;
    @Autowired private HttpSession session;
    
    
    //Hiển thị danh sách màu của sản phẩm
    @GetMapping("admin/color-management/view")
    public String ViewAllColor(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListColor", colorServ.findAllColor());
            return "color/view-manage-color";
        }
    }
    
    //Hiển thị trang thêm màu của sản phẩm
    @GetMapping("admin/color-management/add")
    public String ViewAddColor(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && 
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            return "color/view-manage-add-color";
        }
    }
    
    //Nhận yêu cầu thêm màu của sản phẩm
    @PostMapping("admin/color-management/add")
    public String AddColor(Model model,
            @RequestParam("name") String name){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            Color color = new Color();
            color.setName(name);
            Color colorCreated = colorServ.saveColor(color);
            if (colorCreated!=null) {
                model.addAttribute("message", "Thêm thành công");
            } else {
                model.addAttribute("name", name);
                model.addAttribute("message", "Thêm thất bại");
            }
            return "color/view-manage-add-color";
        }
    }
    
    //Hiển thị trang sửa màu của sản phẩm
    @GetMapping("admin/color-management/edit/{id}")
    public String ViewEditColor(Model model,
            @PathVariable("id") Integer colorId){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {  
            model.addAttribute("Color", colorServ.getColorById(colorId));
            return "color/view-manage-edit-color";
        }
    }
    
    //Nhận yêu cầu sửa màu của sản phẩm
    @PostMapping("admin/color-management/edit/{id}")
    public String EditColor(Model model,
            @PathVariable("id") Integer colorId,
            @RequestParam("name") String name){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            try{
                Color color = colorServ.getColorById(colorId);
                color.setName(name);
                Color colorSaved = colorServ.saveColor(color);
                model.addAttribute("Color", colorSaved);
                model.addAttribute("message", "Sửa thành công");
            }catch(Exception e){
                model.addAttribute("name", name);
                model.addAttribute("message", "Thêm thất bại");
            }
            return "color/view-manage-edit-color";
        }
    }
    
    //Tìm kiếm
    @GetMapping("admin/color-management/search")
    public String SearchColor(Model model,
            @RequestParam("keyword") String keyword){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListColor", colorServ.searchInManage(keyword));
            return "color/view-manage-color";
        }
    }
}
