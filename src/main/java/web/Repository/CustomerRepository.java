
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

    @Query("FROM Customer c WHERE c.username = :userName")
    public Customer findByUserName(@Param("userName")String userName);
    
    @Query("FROM Customer c WHERE c.email = :email")
    public Customer findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.status.statusID = 0 WHERE c.customerID = ?1")
    public void lockById(Integer Id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.status.statusID = 1 WHERE c.customerID = ?1")
    public void unlockById(Integer Id);
    
    @Query("FROM Customer c WHERE c.name LIKE ?1"
            + " OR c.customerID LIKE ?1"
            + " OR c.username LIKE ?1"
            + " OR c.phoneNumber LIKE ?1"
            + " OR c.status.name LIKE ?1"
            + " OR c.email LIKE ?1")
    public List<Customer> searchInManage(String keyword);
      
}
