
package web.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Product> searchProduct(String name,
            List<Integer> suppliers, BigInteger lower,
            BigInteger upper, List<Integer> colors, Integer page, Sort.Order sort) {
        Pageable pageable = PageRequest.of(page, 8);
        return productRepo.searchProduct(name, suppliers, lower, upper, colors, pageable, sort);
    }
    
    @Override
    public List<Product> search10ProductRandomByName(String keywordLatest){
        List<Product> list =  productRepo.searchProductByName("%"+keywordLatest+"%");
        if(list.size()<10){
            return list;
        }
        Random random = new Random();
        List<Product> listResult =  new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(list.size());;
            listResult.add(list.get(randomIndex));
        }
        
        return listResult;
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepo.findById(id).get();
    }

    @Override
    public void lockById(Integer id) {
        productRepo.lockById(id);
    }

    @Override
    public void unlockById(Integer id) {
        productRepo.unlockById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }
    @Override
    public void lockBySupplierId(Integer id) {
        productRepo.lockBySupplierId(id);
    }

    @Override
    public void unlockBySupplierId(Integer id) {
        productRepo.unlockBySupplierId(id);
    }

    @Override
    public List<Product> searchProductInManage(String keyword) {
        return productRepo.searchProductInManage("%"+keyword+"%");
    }
}
