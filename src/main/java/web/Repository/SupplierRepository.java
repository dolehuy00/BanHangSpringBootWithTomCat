
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer>{
    
}
