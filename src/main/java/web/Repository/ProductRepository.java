
package web.Repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, ProductRepositoryCustom{
    
    @Query("FROM Product p ORDER BY p.createAt DESC LIMIT :limit")
    public List<Product> getProductNewest(@Param("limit") Integer limit);
    
    @Query("SELECT MAX(price) FROM Product")
    public BigInteger getMaxPrice();
    
    @Query("SELECT MIN(price) FROM Product")
    public BigInteger getMinPrice();
    
    @Query("FROM Product p WHERE p.name LIKE ?1")
    public List<Product> searchProductByName(String name);  
}
