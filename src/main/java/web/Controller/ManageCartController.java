
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.CartitemPK;
import web.Model.User;
import web.Service.CartItemService;
import web.Service.CartService;

@Controller
public class ManageCartController {
    
    @Autowired private CartService cartServ;    
    @Autowired private HttpSession session;
    @Autowired private CartItemService cartItemServ;
    
    //Xem danh sách các giỏ hàng
    @GetMapping("admin/cart-management/view")
    public String ViewCartManagement(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListCart", cartServ.findAllCart());
            return "cart/cart-management";
        }     
    }
    
    //Xem chi tiết giỏ hàng
    @GetMapping("admin/cart-management/view/{id}")
    public String CartManagementViewCartDetail(Model model, @PathVariable("id") Integer cartID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("Cart", cartServ.getCartById(cartID));   
            return "cart/manage-cart-detail";
        }      
    }
    
    //Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("admin/cart-management/delete")
    @ResponseBody
    public String ManagerDeleteProductInCart(
            @RequestParam("product") Integer productId,
            @RequestParam("color") Integer colorId,
            @RequestParam("cart") Integer cartId
    ){
        JSONObject responseData = new JSONObject();
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            //Xóa sản phẩm trong giỏ hàng
            CartitemPK id = new CartitemPK(cartId, productId, colorId);
            boolean result = cartItemServ.deleteProductCartItem(id);
            //Sửa total price
            BigInteger totalPrice = cartServ.updateTotalPrice(cartId);
            //Sửa total quantity
            Integer totalQuantity = cartServ.updateTotalQuantity(cartId);
            //Trả kết quả cho client
            responseData.put("Reusult", result);
            responseData.put("TotalPrice", totalPrice);
            responseData.put("TotalQuantity", totalQuantity);
            return responseData.toString();
        }   
    }
    //Làm rỗng giỏ hàng bằng id
    @GetMapping("admin/cart-management/empty/{id}")
    public String CartManagementEmptyCart(@PathVariable("id") Integer cartID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            cartItemServ.emptyCartById(cartID);
            cartServ.updateTotalPrice(cartID);
            cartServ.updateTotalQuantity(cartID);
            return "redirect:../view/" + cartID;
        }
    } 
    
    //Tìm kiếm
    @GetMapping("admin/cart-management/search")
    public String SearchCart(Model model, @RequestParam("keyword") String keyword){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListCart", cartServ.searchInManage(keyword));
            return "cart/cart-management";   
        }  
    }
}
