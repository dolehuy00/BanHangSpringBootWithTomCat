
package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Model.Customer;
import web.Service.ProductService;
import web.Service.SliderService;
import web.Service.SupplierService;


@Controller
public class CustomerHomeController {
    
    @Autowired private ProductService prodServ;
    @Autowired private SupplierService supplierServ;
    @Autowired private SliderService sliderServ;
    @Autowired private HttpSession session;
    
    //Xem trang chủ
    @GetMapping("/")
    public String Index(Model model){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        String keywordLatest = "";
        if(customer != null && customer.getSearchLatest() != null){
            keywordLatest = customer.getSearchLatest();
        }
        model.addAttribute("listRecommend", prodServ.search10ProductRandomByName(keywordLatest));
        model.addAttribute("listSlider", sliderServ.getAllSlider());
        model.addAttribute("listSupplier", supplierServ.getAllSupplier());
        model.addAttribute("listNewestProduct", prodServ.getTenProductNewest());
        return "index/index";
    }
}
