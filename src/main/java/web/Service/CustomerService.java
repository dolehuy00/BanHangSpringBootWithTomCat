
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Customer;

@Service
public interface CustomerService {
    public Customer findCustomerByUserName(String username);
    public Customer saveCustomer(Customer customer);
    public boolean isValidAccount(String username, String password);
    public boolean checkExitsAccoutByEmail(String email);
    public Customer findCustomerByEmail(String email);
    public Customer updateCustomer(Customer customer);
    public void updateSearchLastest(Integer id, String keyword);
    public boolean checkExitsAccoutByUsername(String username);
    public List<Customer> findAll();
    public void lockById(Integer Id);
    public void unlockById(Integer Id);
    public Customer findById(Integer customerId);
}
