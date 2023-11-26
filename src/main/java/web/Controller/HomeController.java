
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.ProductService;
import web.Service.SliderService;
import web.Service.SupplierService;


@Controller
public class HomeController {
    
    @Autowired private ProductService prodServ;
    @Autowired private SupplierService supplierServ;
    @Autowired private SliderService sliderServ;
    
    @GetMapping("/")
    public String Index(Model model){
        model.addAttribute("listSlider", sliderServ.getAllSlider());
        model.addAttribute("listSupplier", supplierServ.getAllSupplier());
        model.addAttribute("listNewestProduct", prodServ.getTenProductNewest());
        return "index/index";
    }
}
