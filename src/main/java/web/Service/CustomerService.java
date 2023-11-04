
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.Customer;

@Service
public interface CustomerService {
    public Customer findCustomerByUserName(String username);
    public Customer addNewCustomer(Customer customer);
}
