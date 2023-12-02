
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Cart;
import web.Model.Customer;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>, JpaRepository<Cart, Integer>{
    
    public Cart findByCustomer(Customer customer);
}
