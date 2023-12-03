
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.Customer;

@Service
public interface CustomerService {
    public Customer findCustomerByUserName(String username);
    public Customer addNewCustomer(Customer customer);
    public boolean isValidAccount(String username, String password);
    public String generateRandomPassword(int length);
    public boolean checkExitsAccoutByEmail(String email);
    public Customer findCustomerByEmail(String email);
    public Customer updateCustomer(Customer customer);
    public void updateSearchLastest(Integer id, String keyword);
    public boolean checkExitsAccoutByUsername(String username);
}
