
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.ProductColor;
import web.Model.ProductColorPK;

@Service
public interface ProductColorService {
    public ProductColor getProductColorById(ProductColorPK id);
    public Integer checkValidQuantity(ProductColorPK id, Integer quantity);
}
