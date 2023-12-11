
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;
import web.Repository.StatisticRepository;

@Service
public class StatisticServiceImpl implements StatisticService{

    @Autowired StatisticRepository statisticRepo;
    
    @Override
    public List<CustomerBuyTheMost> getFiveCustomerBuyTheMost() {
        return statisticRepo.getTopCustomersWithTotalPrice(5);
    }

    @Override
    public List<SellerSellTheMost> getFiveSellerSellTheMost() {
        return statisticRepo.getTopSellersWithTotalPrice(5);
    }

    @Override
    public List<RevenueInYear> getRevenueInYear() {
        return statisticRepo.getRevenueInYear();
    }

    @Override
    public List<MostPurchasedProduct> getTenProductPurchasedQuantity() {
        return statisticRepo.getTopProductWithPurchasedQuantity(10);
    }
    
}
