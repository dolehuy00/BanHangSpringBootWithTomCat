
package web.Controller;

import java.math.BigInteger;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;
import web.Service.StatisticService;

@Controller
public class ManageStatisticController {
    
    @Autowired StatisticService stServ;
    
    @GetMapping("admin/statistic")
    public String ViewAll(){
        return "statistic/view-all-statistic";
    }
    
    @GetMapping("admin/statistic-5-customer")
    @ResponseBody
    public String GetStatisticFiveCustomerBuyTheMost(){
        JSONObject response = new JSONObject();
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
    
    @GetMapping("admin/statistic-5-seller")
    @ResponseBody
    public String GetStatisticFiveSellerSellTheMost(){
        JSONObject response = new JSONObject();
        List<SellerSellTheMost> list = stServ.getFiveSellerSellTheMost();
        String[] customers = new String[5];
        BigInteger[] totals = new BigInteger[5];
        int i = 0;
        for (SellerSellTheMost sellerSTM: list) {
            customers[i] = sellerSTM.getName();
            totals[i] = sellerSTM.getTotal();
            i++;
        }
        response.put("sellers", customers);
        response.put("totals", totals);
        return response.toString();
    }
    
    @GetMapping("admin/statistic-revenue-in-year")
    @ResponseBody
    public String GetRevenueInYear(){
        JSONObject response = new JSONObject();
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
    
    @GetMapping("admin/statistic-10-product")
    @ResponseBody
    public String GetStatisticTenPurchasedQuantityTheMost(){
        JSONObject response = new JSONObject();
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
    
    @GetMapping("admin/view-statistic-10-product")
    public String ViewStatisticTenPurchasedQuantityTheMost(){
        List<MostPurchasedProduct> list = stServ.getTenProductPurchasedQuantity();
        
        return "";
    }
    
    @GetMapping("admin/view-statistic-revenue-in-year")
    public String ViewRevenueInYear(){
        List<RevenueInYear> list = stServ.getRevenueInYear();

        return "";
    }
    
    @GetMapping("admin/view-statistic-5-seller")
    public String ViewStatisticFiveSellerSellTheMost(){
        List<SellerSellTheMost> list = stServ.getFiveSellerSellTheMost();

        return "";
    }
    
    @GetMapping("admin/view-statistic-5-customer")
    public String ViewStatisticFiveCustomerBuyTheMost(){
        List<CustomerBuyTheMost> list = stServ.getFiveCustomerBuyTheMost();
        
        return "";
    }
}
