
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;
import web.Model.User;
import web.Service.StatisticService;

@Controller
public class ManageStatisticController {
    
    @Autowired StatisticService stServ;
    @Autowired private HttpSession session;
    
    @GetMapping("admin/statistic")
    public String ViewAll(){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            return "statistic/view-all-statistic";
        }
    }
    
    @GetMapping("admin/statistic-5-customer")
    @ResponseBody
    public String GetStatisticFiveCustomerBuyTheMost(){
        JSONObject response = new JSONObject();
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else if (user.getRole().getRoleID() != 1) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else {
            List<CustomerBuyTheMost> list = stServ.getFiveCustomerBuyTheMost();
            String[] customers = new String[5];
            BigInteger[] totals = new BigInteger[5];
            int i = 0;
            for (CustomerBuyTheMost cusBTM: list) {
                customers[i] = cusBTM.getName();
                totals[i] = cusBTM.getTotal();
                i++;
            }
            response.put("customers", customers);
            response.put("totals", totals);
            return response.toString();
        }
    }
    
    @GetMapping("admin/statistic-5-seller")
    @ResponseBody
    public String GetStatisticFiveSellerSellTheMost(){
        JSONObject response = new JSONObject();
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else if (user.getRole().getRoleID() != 1) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else {
           List<SellerSellTheMost> list = stServ.getFiveSellerSellTheMost();
            String[] sellers = new String[5];
            BigInteger[] totals = new BigInteger[5];
            int i = 0;
            for (SellerSellTheMost sellerSTM: list) {
                sellers[i] = sellerSTM.getName();
                totals[i] = sellerSTM.getTotal();
                i++;
            }
            response.put("sellers", sellers);
            response.put("totals", totals);
            return response.toString();
        }
    }
    
    @GetMapping("admin/statistic-revenue-in-year")
    @ResponseBody
    public String GetRevenueInYear(){
        JSONObject response = new JSONObject();
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else if (user.getRole().getRoleID() != 1) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else {
           List<RevenueInYear> list = stServ.getRevenueInYear();
            BigInteger[] totals = new BigInteger[12];
            for (int i = 0; i < 12; i++) {
                totals[i] = BigInteger.ZERO;
            }
            for (RevenueInYear sellerSTM: list) {
                totals[sellerSTM.getMonth()-1] = sellerSTM.getTotal();
            }
            response.put("totals", totals);
            return response.toString();
        }
    }
    
    @GetMapping("admin/statistic-10-product")
    @ResponseBody
    public String GetStatisticTenPurchasedQuantityTheMost(){
        JSONObject response = new JSONObject();
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else if (user.getRole().getRoleID() != 1) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else {
            List<MostPurchasedProduct> list = stServ.getTenProductPurchasedQuantity();
            String[] products = new String[10];
            Integer[] quantity = new Integer[10];
            BigInteger[] totals = new BigInteger[10]; 
            int i = 0;
            for (MostPurchasedProduct sellerSTM: list) {
                products[i] = sellerSTM.getName();
                totals[i] = sellerSTM.getTotal();
                quantity[i] = sellerSTM.getQuantity();
                i++;
            }
            response.put("products", products);
            response.put("totals", totals);
            response.put("quantity", quantity);
            return response.toString();
        }
    }
    
    @GetMapping("admin/view-statistic-10-product")
    public String ViewStatisticTenPurchasedQuantityTheMost(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            List<MostPurchasedProduct> list = stServ.getTenProductPurchasedQuantity();
            String[] products = new String[10];
            Integer[] quantity = new Integer[10];
            BigInteger[] totals = new BigInteger[10]; 
            int i = 0;
            for (MostPurchasedProduct sellerSTM: list) {
                products[i] = sellerSTM.getName();
                totals[i] = sellerSTM.getTotal();
                quantity[i] = sellerSTM.getQuantity();
                i++;
            }
            model.addAttribute("totals", totals);
            model.addAttribute("quantity", quantity);
            model.addAttribute("products", products);
            model.addAttribute("ListProduct", list);
            return "statistic/view-statistic-purchase-quantity-the-most";
        }
    }
    
    @GetMapping("admin/view-statistic-revenue-in-year")
    public String ViewRevenueInYear(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            List<RevenueInYear> list = stServ.getRevenueInYear();
            BigInteger[] totals = new BigInteger[12];
            for (int i = 0; i < 12; i++) {
                totals[i] = BigInteger.ZERO;
            }
            for (RevenueInYear sellerSTM: list) {
                totals[sellerSTM.getMonth()-1] = sellerSTM.getTotal();
            }
            model.addAttribute("totals", totals);    
            model.addAttribute("ListRevenue", list);
            return "statistic/view-statistic-revenue-in-year";
        } 
    }
    
    @GetMapping("admin/view-statistic-5-seller")
    public String ViewStatisticFiveSellerSellTheMost(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            List<SellerSellTheMost> list = stServ.getFiveSellerSellTheMost();
            String[] sellers = new String[5];
            BigInteger[] totals = new BigInteger[5];
            int i = 0;
            for (SellerSellTheMost sellerSTM: list) {
                sellers[i] = sellerSTM.getName();
                totals[i] = sellerSTM.getTotal();
                i++;
            }
            model.addAttribute("sellers", sellers);
            model.addAttribute("totals", totals);
            model.addAttribute("ListSeller", list);
            return "statistic/view-statistic-seller";
        }  
    }
    
    @GetMapping("admin/view-statistic-5-customer")
    public String ViewStatisticFiveCustomerBuyTheMost(Model model){
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1) {
            return "account/view-role-not-permission";
        } else {
            List<CustomerBuyTheMost> list = stServ.getFiveCustomerBuyTheMost();
            String[] customers = new String[5];
            BigInteger[] totals = new BigInteger[5];
            int i = 0;
            for (CustomerBuyTheMost cusBTM: list) {
                customers[i] = cusBTM.getName();
                totals[i] = cusBTM.getTotal();
                i++;
            }
            model.addAttribute("customers", customers);
            model.addAttribute("totals", totals);
            model.addAttribute("ListCustomer", list);
            return "statistic/view-statistic-customer";
        } 
    }
}
