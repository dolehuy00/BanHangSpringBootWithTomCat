
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.CartitemPK;
import web.Model.Customer;
import web.Model.ProductColorPK;
import web.Service.CartItemService;
import web.Service.CartService;
import web.Service.ProductColorService;

@Controller
public class CartController {
    
    @Autowired private CartService cartServ;    
    @Autowired private HttpSession session;
    @Autowired private CartItemService cartItemServ;
    @Autowired private ProductColorService proColorServ;
    
    //Xem giỏ hàng khách hàng
    @GetMapping("cart")
    public String viewCart(Model model){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            model.addAttribute("cart", cartServ.getCartByCustomer(customer));
            return "cart/view-cart";
        }
        return "account/login";
    }
    
    //Thêm sản phẩm vào giỏ hàng
    @GetMapping("cart/add")
    public String AddProductToCart(){
        return "";
    }

    //Sửa số lượng sản phẩm trong giỏ hàng
    @PostMapping("cart/change")
    @ResponseBody
    public String ChangeQuantityProductInCart(
            @RequestParam("product") Integer productId,
            @RequestParam("color") Integer colorId,
            @RequestParam("quantity") Integer quantity
    ){
        JSONObject responseData = new JSONObject();
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            //kiểm tra số lượng còn lại
            quantity = proColorServ.checkValidQuantity(
                    new ProductColorPK(productId, colorId), quantity);
            //cập nhật số lượng trong giỏ hàng
            Integer cartID =  customer.getCart().getCartID();
            CartitemPK id = new CartitemPK(cartID, productId, colorId);
            cartItemServ.updateQuantityCartItem(id, quantity);
            //Cập nhật total price
            BigInteger totalPrice = cartServ.updateTotalPrice(cartID);
            //Trả về cho client
            responseData.put("TotalPrice", totalPrice);
            responseData.put("Quantity", quantity);
        }
        return responseData.toString();
    }
    
    //Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("cart/delete")
    @ResponseBody
    public String DeleteProductInCart(
            @RequestParam("product") Integer productId,
            @RequestParam("color") Integer colorId
    ){
        JSONObject responseData = new JSONObject();
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            //Xóa sản phẩm trong giỏ hàng
            Integer cartID =  customer.getCart().getCartID();
            CartitemPK id = new CartitemPK(cartID, productId, colorId);
            boolean result = cartItemServ.deleteProductCartItem(id);
            //Sửa total price
            BigInteger totalPrice = cartServ.updateTotalPrice(cartID);
            //Sửa total quantity
            Integer totalQuantity = cartServ.updateTotalQuantity(cartID);
            //Cập nhật thông tin total quantity của người dùng trên session
            customer.getCart().setTotalQuantity(totalQuantity);
            session.setAttribute("CUSTOMER", customer);
            //Trả kết quả cho client
            responseData.put("Reusult", result);
            responseData.put("TotalPrice", totalPrice);
            responseData.put("TotalQuantity", totalQuantity);
        }
        return responseData.toString();
    }
}
