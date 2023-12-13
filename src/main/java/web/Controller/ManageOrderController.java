
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.OrderItem;
import web.Model.OrderStatus;
import web.Model.Orders;
import web.Model.ProductColor;
import web.Model.ProductColorPK;
import web.Model.User;
import web.Service.OrderService;
import web.Service.EmailSenderService;
import web.Service.ProductColorService;

@Controller
public class ManageOrderController {
    
    @Autowired private OrderService orderServ;    
    @Autowired private HttpSession session;
    @Autowired private EmailSenderService emailServ;
    @Autowired private ProductColorService proColorServ;
        
    //Xem danh sách các đơn hàng
    @GetMapping("admin/order-management/view")
    public String ViewOrderManagement(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListOrder", orderServ.findAllOrder());
            return "order/view-order-management";
        }     
    }
    
    //Xem chi tiết đơn hàng
    @GetMapping("admin/order-management/view/{id}")
    public String OrderManagementViewOrderDetail(Model model,
            @PathVariable("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("Order", orderServ.findOrderById(orderID));   
            return "order/view-order-detail-management";
        }      
    }
    
    //Xem danh sách các đơn hàng đang chờ
    @GetMapping("admin/order-awaiting")
    public String ViewOrderAwaiting(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 &&
                  user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "awaiting");
            model.addAttribute("title", "Danh sách đơn hàng đang chờ xác nhận");
            model.addAttribute("ListOrder", orderServ.findOrdersAwaiting());
            return "order/seller-view-list-order";
        }     
    }
    //Xem chi tiết đơn hàng đang chờ
    @GetMapping("admin/order-awaiting/view/{id}")
    public String ViewOrderDetailAwaiting(Model model,
            @PathVariable("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 &&
                  user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "awaiting");
            model.addAttribute("Order", orderServ.findOrderById(orderID));   
            return "order/seller-view-order-detail";
        }      
    }
    
    //Xác nhận đơn hàng đang chờ
    @PostMapping("admin/order-awaiting/confirm")
    public String ConfirmOrderAwaiting(Model model,
            @RequestParam("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 &&
                  user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            Orders order = orderServ.findOrderById(orderID);
            order.setSeller(user);
            order.setStatus(new OrderStatus(2));
            orderServ.saveOrder(order);
            //Gửi email cho khách hàng
            String titleEmail = "Đơn hàng của bạn đã được tiếp nhận,"
                    + " nhân viên của chúng tôi sẽ gọi điện cho bạn để xác nhận!";
            String bodyEmail = "Mã đơn hàng: #"+order.getOrderID()
                                +"\n Nhân viên: "+order.getSeller().getName()
                                +"\n Ngày đặt: "+order.getDate()
                                +"\n Địa chỉ giao: "+order.getAddress()
                                +"\n Số điện thoại của bạn: "+order.getPhoneNumber()
                                +"-----Cảm ơn quý khách đã mua hàng của chúng tôi-----";
            String emailCustomer = order.getCustomerID().getEmail();
            emailServ.sendMail(emailCustomer, titleEmail, bodyEmail);
            return "redirect:/admin/order-confirmed";
        }      
    }
    //Xem danh sách các đơn hàng đã tiếp nhận
    @GetMapping("admin/order-confirmed")
    public String ViewOrderConfirmed(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "confirmed");
            model.addAttribute("title", "Danh sách đơn hàng đã tiếp nhận");
            model.addAttribute("ListOrder", orderServ.findOrdersConfirmed(user.getUserID()));
            return "order/seller-view-list-order";
        }     
    }
    
    //Xem chi tiết đơn hàng đã tiếp nhận
    @GetMapping("admin/order-confirmed/view/{id}")
    public String ViewOrderDetailConfirmed(Model model, @PathVariable("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "confirmed");
            model.addAttribute("Order", orderServ.findOrderById(orderID));   
            return "order/seller-view-order-detail";
        }      
    }
    
    //Giao hàng
    @PostMapping("admin/order-confirmed/deliver")
    public String DeliverOrder(Model model, @RequestParam("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            Orders order = orderServ.findOrderById(orderID);
            order.setSeller(user);
            order.setStatus(new OrderStatus(3));
            orderServ.saveOrder(order);
            //Gửi email cho khách hàng
            String titleEmail = "Đơn hàng đang được giao đến cho bạn.";
            String bodyEmail = "Mã đơn hàng: #"+order.getOrderID()
                                +"\n Nhân viên: "+order.getSeller().getName()
                                +"\n Ngày đặt: "+order.getDate()
                                +"\n Địa chỉ giao: "+order.getAddress()
                                +"\n Số điện thoại của bạn: "+order.getPhoneNumber()
                                +"-----Cảm ơn quý khách đã mua hàng của chúng tôi-----";
            String emailCustomer = order.getCustomerID().getEmail();
            emailServ.sendMail(emailCustomer, titleEmail, bodyEmail);
            return "redirect:/admin/order-delivering";
        }      
    }
    
    //Xem danh sách các đơn hàng đang giao
    @GetMapping("admin/order-delivering")
    public String ViewOrderDelivering(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "delivering");
            model.addAttribute("ListOrder", orderServ.findOrdersDelivering(user.getUserID()));
            return "order/seller-view-list-order";
        }     
    }
    
    //Xem chi tiết đơn hàng đang giao
    @GetMapping("admin/order-delivering/view/{id}")
    public String ViewOrderDetailDelivering(Model model,
            @PathVariable("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "delivering");
            model.addAttribute("Order", orderServ.findOrderById(orderID));   
            return "order/seller-view-order-detail";
        }      
    }
    
    
    //Hoàn thành đơn hàng
    @PostMapping("admin/order-delivering/finish")
    public String FinishOrder(Model model, @RequestParam("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            Orders order = orderServ.findOrderById(orderID);
            order.setSeller(user);
            order.setStatus(new OrderStatus(4));
            orderServ.saveOrder(order);
            //Gửi email cho khách hàng
            String titleEmail = "Đơn hàng đã được giao thành công.";
            String bodyEmail = "Mã đơn hàng: #"+order.getOrderID()
                                +"\n Nhân viên: "+order.getSeller().getName()
                                +"\n Ngày đặt: "+order.getDate()
                                +"\n Địa chỉ giao: "+order.getAddress()
                                +"\n Số điện thoại của bạn: "+order.getPhoneNumber()
                                +"-----Cảm ơn quý khách đã mua hàng của chúng tôi-----";
            String emailCustomer = order.getCustomerID().getEmail();
            emailServ.sendMail(emailCustomer, titleEmail, bodyEmail);
            return "redirect:/admin/order-finished";
        }      
    }
    
    //Xem danh sách các đơn hàng đã hoàn thành
    @GetMapping("admin/order-finished")
    public String ViewOrderFinished(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "finished");
            model.addAttribute("ListOrder", orderServ.findOrdersFinished(user.getUserID()));
            return "order/seller-view-list-order";
        }     
    }
    
    //Xem chi tiết đơn hàng đã hoàn thành
    @GetMapping("admin/order-finished/view/{id}")
    public String ViewOrderDetailFinished(Model model,
            @PathVariable("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("view", "finished");
            model.addAttribute("Order", orderServ.findOrderById(orderID));   
            return "order/seller-view-order-detail";
        }      
    }
    
    //Hủy đơn
    @PostMapping("admin/order/cancel")
    public String CancelOrder(Model model, @RequestParam("id") Integer orderID){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1 && user.getRole().getRoleID() != 2){
            return "account/view-role-not-permission";
        }else{
            Orders order = orderServ.findOrderById(orderID);
            if(order.getStatus().getOrderStatusId() != 4){
                order.setSeller(user);
                order.setStatus(new OrderStatus(5));
                orderServ.saveOrder(order);
                //Đặt lại số lượng sản phẩm
                List<OrderItem> orderItem = order.getOrderItemList();
                List<ProductColor> productColors = new ArrayList<>();
                for (OrderItem item : orderItem){
                    //Tru so luong trong kho
                    Integer productId = item.getProduct().getProductID();
                    Integer colorId = item.getColor().getColorID();
                    ProductColor productColor = proColorServ.getProductColorById(
                            new ProductColorPK(productId,colorId));
                    productColor.setQuantity(productColor.getQuantity()+item.getQuantity());
                    productColors.add(productColor);
                }
                proColorServ.saveListProductColor(productColors);
                //Gửi email cho khách hàng
                String titleEmail = "Đơn hàng đã hủy.";
                String bodyEmail = "Mã đơn hàng: #"+order.getOrderID()
                                    +"\n Nhân viên hủy: "+order.getSeller().getName()
                                    +"\n Ngày đặt: "+order.getDate()
                                    +"\n Địa chỉ giao: "+order.getAddress()
                                    +"\n Số điện thoại của bạn: "+order.getPhoneNumber()
                                    +"-----Cảm ơn quý khách-----";
                String emailCustomer = order.getCustomerID().getEmail();
                emailServ.sendMail(emailCustomer, titleEmail, bodyEmail);
            }
            return "redirect:/admin/order-finished";
        }      
    }
    //Tìm kiếm
    @GetMapping("admin/order-management/search")
    public String SearchOrder(Model model,
            @RequestParam("keyword") String keyword){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if(user == null){
            return "redirect:/admin/login";
        } else if(user.getRole().getRoleID()!=1){
            return "account/view-role-not-permission";
        }else{
            model.addAttribute("ListOrder", orderServ.searchInManage(keyword));
             return "order/view-order-management";
        }  
    }
}
