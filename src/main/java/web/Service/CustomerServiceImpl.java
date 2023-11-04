
package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Customer;
import web.Repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository cusRepo;
        
    @Override
    public Customer findCustomerByUserName(String username) {
        try{
            Customer customer =  cusRepo.findByUserName(username);
            return customer;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return cusRepo.save(customer);
    }
  
}
