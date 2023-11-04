
package web.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.Model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
    
    
    @Query("FROM Customer c WHERE c.username = :userName")
    public Customer findByUserName(@Param("userName")String userName);
}
