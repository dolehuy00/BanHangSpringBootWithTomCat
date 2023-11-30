
package web.Service;

import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Product;

@Service
public interface ProductService {
    
    public List<Product> getTenProductNewest();
    public List<Product> findAllProduct();
    public BigInteger getMaxPrice();
    public BigInteger getMinPrice();
    public List<Product> searchProductByName(
            String name, Integer[] suppliers,
            BigInteger lower, BigInteger upper,
            Integer[] colors);
}
