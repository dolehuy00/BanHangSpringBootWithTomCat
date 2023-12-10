
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.DTO.ItemCheckOutDTO;
import web.Model.CartitemPK;
import web.Model.Color;
import web.Model.Customer;
import web.Model.OrderItem;
import web.Model.OrderItemPK;
import web.Model.OrderStatus;
import web.Model.Orders;
import web.Model.Product;
import web.Model.ProductColor;
import web.Model.ProductColorPK;
import web.Service.CartItemService;
import web.Service.CartService;
import web.Service.ColorService;
import web.Service.EmailSenderService;
import web.Service.OrderService;
import web.Service.ProductColorService;
import web.Service.ProductService;

@Controller
public class CustomerOrderController {
    @Autowired private OrderService orderServ;
    @Autowired private ColorService colorServ;
    @Autowired private ProductService proServ;
    @Autowired private HttpSession session;
    @Autowired private ProductColorService proColServ;
    @Autowired private CartItemService cartItemServ;
    @Autowired private EmailSenderService emailServ;
    @Autowired private CartService cartServ;
    
    //Khách hàng tự xem các đơn hàng đã đặt
    @GetMapping("profile/view-order")
    public String ViewOrder(Model model, @RequestParam("id") Integer id){
        if(id != null){
            Orders order = orderServ.findOrderById(id);
            model.addAttribute("Order", order);
            model.addAttribute("OrderItems", order.getOrderItemList());
        }
        return "order/view-order-detail";
    }
    
    //Gửi yêu cầu tạo đơn hàng
    @PostMapping("pre-checkout")
    public String preCheckOut(@RequestBody()ItemCheckOutDTO[] items , RedirectAttributes redirectAttributes){
        //Gửi list item của nhận từ request sang view-checkout
        redirectAttributes.addFlashAttribute("list", items);
        return "redirect:/view-checkout";
    }
    
    //Preview đơn hàng
    @GetMapping("view-checkout")
    public String ViewCheckOut(Model model){   
        ItemCheckOutDTO[] listItem = (ItemCheckOutDTO[]) model.asMap().get("list");
        //Kiểm tra thông tin khách hàng
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            if(listItem != null) {
                //Tạo đơn hàng mới
                Orders newOrder = new Orders();
                newOrder.setCustomerID(customer);
                newOrder.setDate(LocalDateTime.now());
                List<OrderItem> listOrderItem = new ArrayList<>();
                BigInteger totalPrice = new BigInteger("0");
                //Tạo các item cho đơn hàng
                for (ItemCheckOutDTO item : listItem) {
                    OrderItem orderItem =  new OrderItem(new OrderItemPK());
                    //Màu
                    Color color = colorServ.getColorById(item.getColor());
                    orderItem.setColor(color);
                    //Sản phẩm
                    Product product = proServ.getProductById(item.getProduct());
                    orderItem.setProduct(product);
                    //ID
                    orderItem.getOrderItemPK().setColorID(color.getColorID());
                    orderItem.getOrderItemPK().setProductID(product.getProductID());
                    //Kiểm tra số lượng
                    ProductColorPK proColId = new ProductColorPK(
                            product.getProductID(), color.getColorID());
                    ProductColor productColor = proColServ.getProductColorById(proColId);
                    Integer quantity = item.getQuantity();
                    if (quantity > productColor.getQuantity()) {
                        quantity = productColor.getQuantity();
                    }
                    //xu ly het hang
                    orderItem.setProductColor(productColor);
                    //Số lượng
                    orderItem.setQuantity(quantity);
                    //Giá
                    totalPrice = totalPrice.add(product.getPrice().multiply(BigInteger.valueOf(quantity)));
                    listOrderItem.add(orderItem);
                }
                //Gửi lên session
                newOrder.setOrderItemList(listOrderItem);
                newOrder.setTotalPrice(totalPrice);
                session.setAttribute("NewOrder", newOrder);
            }
        }else{
            return "redirect:/login";
        }
        return "order/view-check-out";
    }
    
    //Người dùng xác nhận đơn hàng
    @PostMapping("checkout")
    public String CheckOut(@RequestParam("phone-number")String phoneNumber,
                           @RequestParam("address")String address){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            Integer cartId = customer.getCart().getCartID();
            //Lấy order preview từ session
            Orders order = (Orders) session.getAttribute("NewOrder");
            List<OrderItem> orderItem = order.getOrderItemList();
            order.setOrderItemList(null);
            order.setAddress(address);
            order.setPhoneNumber(phoneNumber);
            order.setStatus(new OrderStatus(1));
            //Lưu để tạo id cho order
            Orders newOrder = orderServ.saveOrder(order);
            //Đặt thiết lập list item cho order
            List<ProductColor> productColors = new ArrayList<>();
            List<CartitemPK> cartItems = new ArrayList<>();
            String infoListItemEmail = "";
            for (OrderItem item : orderItem) {
                item.setOrders(newOrder);
                item.getOrderItemPK().setOrderID(newOrder.getOrderID());
                //Tru so luong trong kho
                Integer productId = item.getProduct().getProductID();
                Integer colorId = item.getColor().getColorID();
                ProductColor productColor = proColServ.getProductColorById(
                        new ProductColorPK(productId,colorId));
                productColor.setQuantity(productColor.getQuantity()-item.getQuantity());
                productColors.add(productColor);
                cartItems.add(new CartitemPK(cartId,productId,colorId));
                infoListItemEmail += "======================"
                                   + "Mã sản phẩm: " + productId + "\n"
                                   + "Tên sản phẩm: " + item.getProduct().getName() + "\n"
                                   + "Màu sắc: " + item.getColor().getName()+ "\n"
                                   + "Giá: "+item.getProduct().getPrice() + "\n"
                                   + "Số lượng: "+item.getQuantity() + "\n";
            }
            newOrder.setOrderItemList(orderItem);
            //Lưu lại vào cơ sở dữ liệu
            orderServ.saveOrder(newOrder);
            //Lưu lại số lượng mới
            proColServ.saveListProductColor(productColors);
            //Xóa sản phẩm trong giỏ hàng
            cartItemServ.deleteListCartItemById(cartItems);
            //Cập nhật tổng số lượng sản phẩm trong giỏ hàng
            Integer newTotalQuantity = cartServ.updateTotalQuantity(cartId);
            customer.getCart().setTotalQuantity(newTotalQuantity);
            session.setAttribute("CUSTOMER", customer);
            //Gửi email đặt hàng thành công
            String titleEmail = "Đã đặt hàng thành công, đơn hàng đang chờ xét duyệt!";
            String bodyEmail = "Mã đơn hàng: #"+newOrder.getOrderID()
                                +"\n Ngày đặt: "+newOrder.getDate()
                                +"\n Địa chỉ: "+newOrder.getAddress()
                                +"\n Số điện thoại: "+newOrder.getPhoneNumber()
                                +"\n Danh sách sản phẩm:\n"
                                +infoListItemEmail
                                +"-----Cảm ơn quý khách đã mua hàng của chúng tôi-----";
            String emailCustomer = customer.getEmail();
            emailServ.sendMail(emailCustomer, titleEmail, bodyEmail);
            return "redirect:/checkout-success";
        }
        return "order/view-check-out-error";
    }
    
    @GetMapping("checkout-success")
    public String CheckOutSuccess(){
        return "order/view-check-out-sucesss";
    }
}
