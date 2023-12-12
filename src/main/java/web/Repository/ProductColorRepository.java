
package web.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.ProductColor;
import web.Model.ProductColorPK;

@Repository
public interface ProductColorRepository extends CrudRepository<ProductColor, ProductColorPK>{
    @Modifying
    @Transactional
    @Query("UPDATE ProductColor p SET p.status.statusID = 0 WHERE p.productColorPK = ?1")
    public void lockById(ProductColorPK Id);
 
    @Modifying
    @Transactional
    @Query("UPDATE ProductColor p SET p.status.statusID = 1 WHERE p.productColorPK = ?1")
    public void unlockById(ProductColorPK Id);
}
