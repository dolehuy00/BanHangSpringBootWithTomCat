
package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.ColorService;
import web.Service.ProductService;
import web.Service.SupplierService;

@Controller
public class ProductController {
    @Autowired private ProductService productServ;
    @Autowired private ColorService colorServ;
    @Autowired private SupplierService supplierServ;
    
    @GetMapping("search-product")
    public String SearchProduct(Model model){
        model.addAttribute("ListSupplier", supplierServ.getAllSupplier());
        model.addAttribute("ListColor", colorServ.findAllColor());
        model.addAttribute("ListProduct", productServ.findAllProduct());
        return "product/search-product";
    }
}
