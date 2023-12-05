
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.Cart;
import web.Model.Cartitem;
import web.Model.CartitemPK;
import web.Model.Color;
import web.Model.Customer;
import web.Model.Product;
import web.Model.ProductColor;
import web.Model.ProductColorPK;
import web.Service.CartItemService;
import web.Service.CartService;
import web.Service.ColorService;
import web.Service.ProductColorService;
import web.Service.ProductService;

@Controller
public class CartController {
    
    @Autowired private CartService cartServ;    
    @Autowired private HttpSession session;
    @Autowired private CartItemService cartItemServ;
    @Autowired private ProductColorService proColorServ;
    @Autowired private ProductService productServ;
    @Autowired private ColorService colorServ;
    
    //Xem giỏ hàng khách hàng
    @GetMapping("cart")
    public String viewCart(Model model){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            model.addAttribute("cart", cartServ.getCartByCustomer(customer));
            return "cart/view-cart";
        }
        return "redirect:/login";
    }
    
    //Thêm sản phẩm vào giỏ hàng
    @PostMapping("cart/add")
    @ResponseBody
    public String AddProductToCart(Model model,
            @RequestParam("product") Integer productId,
            @RequestParam("color")Integer colorID,
            @RequestParam("quantity") Integer quantity){
        JSONObject response = new JSONObject();
        //Kiểm tra thông tin khách hàng
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer == null){
            response.put("Redirect", "/banhang/login");
            return response.toString();
        }
        //Thông tin giỏ hàng
        Cart cart = customer.getCart();
        Integer cartID;
        if(cart != null){
            cartID = cart.getCartID();
        }else{
            cart = cartServ.createEmptyCartForCustomer(customer);
            cartID = cart.getCartID();
            customer.setCart(cart);
        }
        CartitemPK cartItemID = new CartitemPK(cartID, productId, colorID);
        //Kiểm tra số lượng còn lại
        ProductColorPK productColor = new ProductColorPK(productId, colorID);
        ProductColor productInData = proColorServ.getProductColorById(productColor);
        Cartitem itemExitsInCart = cartItemServ.getCartItemById(cartItemID);   
        Integer newQuantity = quantity;
        if(itemExitsInCart != null){
            newQuantity += itemExitsInCart.getQuantity();
        }
        if(newQuantity > productInData.getQuantity()){
            newQuantity = productInData.getQuantity();
            response.put("MessageMaxQuantity", "Chỉ có thể thêm tối đa "+newQuantity+" sản phẩm này vào giỏ hàng!");
        }
        //Tạo item
        Cartitem cartItemRequest = new Cartitem();
        cartItemRequest.setQuantity(newQuantity);
        cartItemRequest.setCartitemPK(cartItemID);
        //Thêm vào cơ sở dữ liệu
        cartItemServ.addCartItem(cartItemRequest);
        //Cập nhật tổng số lượng sản phẩm trong giỏ hàng
        Integer totalQuantity = cartServ.updateTotalQuantity(cartID);
        response.put("QuantityProductInCart", totalQuantity);
        //Cập nhật thông tin total quantity của người dùng trên session
        customer.getCart().setTotalQuantity(totalQuantity);
        session.setAttribute("CUSTOMER", customer);
        //Trả về phản hồi
        response.put("Success", true);
        return response.toString();
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
