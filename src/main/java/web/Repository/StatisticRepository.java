
package web.Repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;

@Repository
public interface StatisticRepository {
    public List<CustomerBuyTheMost> getTopCustomersWithTotalPrice(int limit);
    public List<SellerSellTheMost> getTopSellersWithTotalPrice(int limit);
    public List<RevenueInYear> getRevenueInYear();
    public List<MostPurchasedProduct> getTopProductWithPurchasedQuantity(int limit);
}
