
package web.Service;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.Model.Product;

@Service
public interface ProductService {
    
    public List<Product> getTenProductNewest();
    public List<Product> findAllProduct();
    public BigInteger getMaxPrice();
    public BigInteger getMinPrice();
    public Page<Product> searchProduct(
            String name, List<Integer> suppliers,
            BigInteger lower, BigInteger upper,
            List<Integer> colors, Integer page, Sort.Order sort);
    public List<Product> search10ProductRandomByName(String keywordLatest);
    public Product getProductById(Integer id);
}
