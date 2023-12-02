
package web.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            BigInteger upper, List<Integer> colors, Integer page) {
        Pageable pageable = PageRequest.of(page, 12);
        return productRepo.searchProduct(name, suppliers, lower, upper, colors, pageable);
    }
    
    @Override
    public List<Product> search10ProductRandomByName(String keywordLatest){
        System.err.println(keywordLatest);
        List<Product> list =  productRepo.searchProductByName("%"+keywordLatest+"%");
        if(list.size()<10){
            System.err.println(list.size());
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
}
