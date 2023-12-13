
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer>{
    
    @Modifying
    @Transactional
    @Query("UPDATE Supplier s SET s.status.statusID = 1 WHERE s.supplierID = ?1")
    public void unlockById(Integer supplierId);
    
    @Modifying
    @Transactional
    @Query("UPDATE Supplier s SET s.status.statusID = 0 WHERE s.supplierID = ?1")
    public void lockById(Integer supplierId);
    
    @Query("FROM Supplier s WHERE s.name LIKE ?1"
            + " OR s.supplierID LIKE ?1"
            + " OR s.status.name LIKE ?1")
    public List<Supplier> searchInManage(String keyword);
}
