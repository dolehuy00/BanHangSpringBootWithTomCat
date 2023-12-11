
package web.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
import web.Model.CustomerBuyTheMost;
import web.Model.MostPurchasedProduct;
import web.Model.RevenueInYear;
import web.Model.SellerSellTheMost;

@Repository
public class StatisticRepositoryImpl implements StatisticRepository{
    
    @PersistenceContext private EntityManager entityManager;
    
    @Override
    public List<CustomerBuyTheMost> getTopCustomersWithTotalPrice(int limit) {
        String queryString = "SELECT orders.CustomerID, customers.name, SUM(total_price) AS total"+ 
                " FROM orders" +
                " JOIN customers on customers.CustomerID = orders.CustomerID"+
                " GROUP BY CustomerID" +
                " ORDER BY total DESC" +
                " LIMIT :limit";
        Query query = entityManager.createNativeQuery(queryString, CustomerBuyTheMost.class);
        query.setParameter("limit", limit);

        List<CustomerBuyTheMost> result = query.getResultList();
        return result;
    }
    
    @Override
    public List<SellerSellTheMost> getTopSellersWithTotalPrice(int limit) {
        String queryString = "SELECT orders.seller, users.name, SUM(total_price) AS total"
                + " FROM orders"
                + " JOIN users on users.UserID = orders.seller"
                + " GROUP BY seller"
                + " ORDER BY total DESC"
                + " LIMIT :limit";
        Query query = entityManager.createNativeQuery(queryString, SellerSellTheMost.class);
        query.setParameter("limit", limit);

        List<SellerSellTheMost> result = query.getResultList();
        return result;
    }
    
    @Override
    public List<RevenueInYear> getRevenueInYear() {
        String queryString = "SELECT MONTH(date) AS month, SUM(total_price) AS total" +
                " FROM orders" +
                " GROUP BY MONTH(date)";
        Query query = entityManager.createNativeQuery(queryString, RevenueInYear.class);
        List<RevenueInYear> result = query.getResultList();
        return result;
    }
    @Override
    public List<MostPurchasedProduct> getTopProductWithPurchasedQuantity(int limit) {
        String queryString = "SELECT orderitems.ProductID, products.Name,"
                    + " SUM(Quantity) AS quantity, SUM(products.Price*Quantity) AS total" +
                    " FROM orderitems" +
                    " JOIN products on products.ProductID = orderitems.ProductID" +
                    " GROUP BY ProductID" +
                    " ORDER BY quantity DESC" +
                    " LIMIT :limit";
        Query query = entityManager.createNativeQuery(queryString, MostPurchasedProduct.class);
        query.setParameter("limit", limit);

        List<MostPurchasedProduct> result = query.getResultList();
        return result;
    }
    
}
