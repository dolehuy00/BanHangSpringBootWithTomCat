
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;

@Service
public interface StatisticService {
    public List<CustomerBuyTheMost> getFiveCustomerBuyTheMost();
    public List<SellerSellTheMost> getFiveSellerSellTheMost();
    public List<RevenueInYear> getRevenueInYear();
    public List<MostPurchasedProduct> getTenProductPurchasedQuantity();
}
