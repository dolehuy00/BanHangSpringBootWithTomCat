
package web.Repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, ProductRepositoryCustom{
    
    @Query("FROM Product p WHERE p.status.statusID = 1 ORDER BY p.createAt DESC LIMIT :limit")
    public List<Product> getProductNewest(@Param("limit") Integer limit);
    
    @Query("SELECT MAX(price) FROM Product WHERE status.statusID = 1")
    public BigInteger getMaxPrice();
    
    @Query("SELECT MIN(price) FROM Product WHERE status.statusID = 1")
    public BigInteger getMinPrice();
    
    @Query("FROM Product p WHERE p.name LIKE ?1 AND p.status.statusID = 1")
    public List<Product> searchProductByName(String name);  
    
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.status.statusID = 0 WHERE p.productID = ?1")
    public void lockById(Integer productId);
 
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.status.statusID = 1 WHERE p.productID = ?1")
    public void unlockById(Integer productId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.status.statusID = 1 WHERE p.supplier.supplierID = ?1")
    public void unlockBySupplierId(Integer supplierId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.status.statusID = 0 WHERE p.supplier.supplierID = ?1")
    public void lockBySupplierId(Integer supplierId);
    
    @Query("FROM Product p WHERE p.name LIKE ?1"
            + " OR p.supplier.name LIKE ?1"
            + " OR p.status.name LIKE ?1"
            + " OR p.productID LIKE ?1")
    public List<Product> searchProductInManage(String keyword);
}
