
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.ProductColor;
import web.Model.ProductColorPK;

@Service
public interface ProductColorService {
    public ProductColor getProductColorById(ProductColorPK id);
    public Integer checkValidQuantity(ProductColorPK id, Integer quantity);
    public ProductColor saveProductColor(ProductColor productColor);
    public void saveListProductColor(List<ProductColor> productColors);
}
