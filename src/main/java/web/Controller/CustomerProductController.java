
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Model.Customer;
import web.Model.Product;
import web.Model.ProductColor;
import web.Model.Review;
import web.Service.ColorService;
import web.Service.CustomerService;
import web.Service.ProductService;
import web.Service.ReviewService;
import web.Service.SupplierService;

@Controller
public class CustomerProductController {
    @Autowired private ProductService productServ;
    @Autowired private ColorService colorServ;
    @Autowired private SupplierService supplierServ;
    @Autowired private HttpSession session;
    @Autowired private CustomerService customerServ;
    @Autowired private ReviewService reviewServ;
    
    //Tìm kiếm bằng thanh tìm kiếm của phần tiêu đề
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
                suppliers, lower, upper, colors, 0,
                new Sort.Order(Sort.Direction.ASC, "price"));
        model.addAttribute("CountProduct", page.getTotalElements());
        model.addAttribute("CountPage", page.getTotalPages());
        model.addAttribute("ListProduct", page.getContent());
        return "product/search-product";
    }
    
    //Lọc sản phẩm
    @GetMapping("search-product-ajax")
    public String SearchProductAjax(Model model,
                @RequestParam(defaultValue = "")String keyword,
                @RequestParam(defaultValue = "")List<Integer> suppliers,
                @RequestParam(defaultValue = "")List<Integer> colors,
                @RequestParam(defaultValue = "")BigInteger lower,
                @RequestParam(defaultValue = "")BigInteger upper,
                @RequestParam(defaultValue = "1")Integer pageNumber,
                @RequestParam(defaultValue = "") String sortPrice)
    {
        //Kiểm tra thông tin khách hàng để lưu keyword tìm kiếm gần nhất
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if(customer!=null){
            customer.setSearchLatest(keyword);
            session.setAttribute("CUSTOMER", customer);
            customerServ.updateSearchLastest(customer.getCustomerID(), keyword);
        }
        //Sắp xếp
        Sort.Order sortOrderPrice = null;
        if(sortPrice.equals("asc")){
            sortOrderPrice = new Sort.Order(Sort.Direction.ASC, "price");
        }else if (sortPrice.equals("desc")) {
            sortOrderPrice = new Sort.Order(Sort.Direction.DESC, "price");
        }
        //Tìm kiếm
        Page<Product> page = productServ.searchProduct(
                "%"+keyword+"%", suppliers, lower, upper,
                colors, pageNumber-1,sortOrderPrice);
        model.addAttribute("CountProduct", page.getTotalElements());
        model.addAttribute("CountPage", page.getTotalPages());
        model.addAttribute("ListProduct", page.getContent());
        return "product/search-product-ajax";
    }
    //Xem chi tiết sản phẩm
    @GetMapping("product/{id}")
    public String ViewDetail(Model model,
            @PathVariable("id") Integer productId){
        Product product = productServ.getProductById(productId);
        List<ProductColor> newListPC = new ArrayList<>();
        for (ProductColor productColor : product.getProductColorList()) {
            if (productColor.getStatus().getStatusID() == 1) {
                newListPC.add(productColor);
            }
        }
        product.setProductColorList(newListPC);
        model.addAttribute("AverageStart", reviewServ.getAverageStarReview(product));
        model.addAttribute("Product", product);
        return "product/product-detail";
    }
    
    @PostMapping("product/review")
    public String CustomerReview(
                    @RequestParam("substance") String substance,
                    @RequestParam("rating") Float star,
                    @RequestParam("product") Integer productID){
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if (customer != null) {
            Review newReview = new Review();
            newReview.setCutomerID(customer);
            newReview.setStar(star);
            newReview.setSubstance(substance);
            newReview.setProductID(new Product(productID));
            newReview.setReviewAt(LocalDateTime.now());
            reviewServ.saveReview(newReview);
        } else {
            return "redirect:/login";
        }
        return "redirect:"+productID;
    }
}
