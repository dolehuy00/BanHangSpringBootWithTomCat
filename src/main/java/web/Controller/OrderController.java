
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Orders;
import web.Service.OrderService;

@Controller
public class OrderController {
    @Autowired private OrderService orderServ;
    
    
    @GetMapping("profile/view-order")
    public String ViewOrder(Model model, @RequestParam("id") Integer id){
        if(id !=null){
            Orders order = orderServ.findOrderById(id);
            model.addAttribute("Order", order);
            model.addAttribute("OrderItems", order.getOrderItemList());
        }
        return "order/view-order-detail";
    }
}
