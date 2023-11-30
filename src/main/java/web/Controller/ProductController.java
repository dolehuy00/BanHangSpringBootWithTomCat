
package web.Controller;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Color;
import web.Model.Supplier;
import web.Service.ColorService;
import web.Service.ProductService;
import web.Service.SupplierService;

@Controller
public class ProductController {
    @Autowired private ProductService productServ;
    @Autowired private ColorService colorServ;
    @Autowired private SupplierService supplierServ;
    
    @GetMapping("search-product")
    public String SearchProduct(Model model,
                @RequestParam(defaultValue = "")String keyword,
                @RequestParam(defaultValue = "")Integer[] suppliers,
                @RequestParam(defaultValue = "")Integer[] colors,
                @RequestParam(defaultValue = "0")BigInteger lower,
                @RequestParam(defaultValue = "999999999")BigInteger upper)
    {
        List<Supplier> listSupplier = supplierServ.getAllSupplier();
        List<Color> listColor = colorServ.findAllColor();
        if(suppliers.length==0){
            suppliers = new Integer[listSupplier.size()];
            for (int i = 0; i < listSupplier.size(); i++) {
                suppliers[i] = listSupplier.get(i).getSupplierID();
            }
        }
        if(colors.length==0){
            colors = new Integer[listColor.size()];
            for (int i = 0; i < listColor.size(); i++) {
                colors[i] = listColor.get(i).getColorID();
            }
        }
        model.addAttribute("MaxPrice", productServ.getMaxPrice());
        model.addAttribute("MinPrice", productServ.getMinPrice());
        model.addAttribute("ListSupplier", listSupplier);
        model.addAttribute("ListColor", listColor);
        model.addAttribute("ListProduct", productServ.searchProductByName(
                "%"+keyword+"%", suppliers, lower, upper, colors));
        return "product/search-product";
    }
}
