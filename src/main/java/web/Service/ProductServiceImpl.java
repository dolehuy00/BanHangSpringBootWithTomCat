
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Product;
import web.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired private ProductRepository productRepo;
    
    @Override
    public List<Product> getTenProductNewest() {
        return productRepo.getProductNewest(10);
    }
    
}
