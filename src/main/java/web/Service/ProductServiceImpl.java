
package web.Service;

import java.math.BigInteger;
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

    @Override
    public List<Product> findAllProduct() {
        return (List<Product>) productRepo.findAll();
    }
    
    @Override
    public BigInteger getMaxPrice(){
        return productRepo.getMaxPrice();
    }
    
    @Override
    public BigInteger getMinPrice(){
        return productRepo.getMinPrice();
    }

    @Override
    public List<Product> searchProductByName(String name,
            Integer[] suppliers, BigInteger lower,
            BigInteger upper, Integer[] colors) {
        return productRepo.searchProduct(name, suppliers, lower, upper, colors);
    }
}
