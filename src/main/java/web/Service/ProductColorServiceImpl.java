
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.ProductColor;
import web.Model.ProductColorPK;
import web.Repository.ProductColorRepository;

@Service
public class ProductColorServiceImpl implements ProductColorService{

    @Autowired private ProductColorRepository productColorRepo;
    
    @Override
    public ProductColor getProductColorById(ProductColorPK id) {
        return productColorRepo.findById(id).get();
    }

    @Override
    public Integer checkValidQuantity(ProductColorPK id, Integer quantity) {
        ProductColor p = productColorRepo.findById(id).get();
        if(quantity <= p.getQuantity()){
            return quantity;
        }
        return p.getQuantity();
    }

    @Override
    public ProductColor saveProductColor(ProductColor productColor) {
        return productColorRepo.save(productColor);
    }

    @Override
    public void saveListProductColor(List<ProductColor> productColors) {
        productColorRepo.saveAll(productColors);
    }
    
}
