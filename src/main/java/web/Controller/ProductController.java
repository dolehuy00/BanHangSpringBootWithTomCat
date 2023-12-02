
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Customer;
import web.Model.Product;
import web.Service.ColorService;
import web.Service.CustomerService;
import web.Service.ProductService;
import web.Service.SupplierService;

@Controller
public class ProductController {
    @Autowired private ProductService productServ;
    @Autowired private ColorService colorServ;
    @Autowired private SupplierService supplierServ;
    @Autowired private HttpSession session;
    @Autowired private CustomerService customerServ;
    
    @GetMapping("search-product")
    public String SearchProduct(Model model,
                @RequestParam(defaultValue = "")String keyword,
                @RequestParam(defaultValue = "")List<Integer> suppliers,
                @RequestParam(defaultValue = "")List<Integer> colors,
                @RequestParam(defaultValue = "")BigInteger lower,
                @RequestParam(defaultValue = "")BigInteger upper)
    {
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer != null){
            customer.setSearchLatest(keyword);
            session.setAttribute("CUSTOMER", customer);
            customerServ.updateSearchLastest(customer.getCustomerID(), keyword);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("MaxPrice", productServ.getMaxPrice());
        model.addAttribute("MinPrice", productServ.getMinPrice());
        model.addAttribute("ListSupplier", supplierServ.getAllSupplier());
        model.addAttribute("ListColor", colorServ.findAllColor());
        Page<Product> page = productServ.searchProduct("%"+keyword+"%",
                suppliers, lower, upper, colors, 0);
        model.addAttribute("CountProduct", page.getTotalElements());
        model.addAttribute("CountPage", page.getTotalPages());
        model.addAttribute("ListProduct", page.getContent());
        return "product/search-product";
    }
    
    @GetMapping("search-product-ajax")
    public String SearchProductAjax(Model model,
                @RequestParam(defaultValue = "")String keyword,
                @RequestParam(defaultValue = "")List<Integer> suppliers,
                @RequestParam(defaultValue = "")List<Integer> colors,
                @RequestParam(defaultValue = "")BigInteger lower,
                @RequestParam(defaultValue = "")BigInteger upper,
                @RequestParam(defaultValue = "1")Integer page)
    {
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer!=null){
            customer.setSearchLatest(keyword);
            session.setAttribute("CUSTOMER", customer);
            customerServ.updateSearchLastest(customer.getCustomerID(), keyword);
        }
        model.addAttribute("ListProduct", productServ.searchProduct(
                "%"+keyword+"%", suppliers, lower, upper, colors, page-1).getContent());
        return "product/list-product-ajax";
    }
}
