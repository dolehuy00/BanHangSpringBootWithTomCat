
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Product;

@Service
public interface ProductService {
    
    public List<Product> getTenProductNewest();
    
}
