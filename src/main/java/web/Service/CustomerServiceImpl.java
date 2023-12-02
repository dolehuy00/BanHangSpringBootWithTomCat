
package web.Service;

import java.util.Random;
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
    
    @Override
    public boolean isValidAccount(String username, String password) {
        Customer customer = cusRepo.findByUserName(username);
        return customer != null && customer.getPassword().equals(password);
    }
    
    @Override
    public boolean checkExitsAccoutByEmail(String email){
        Customer customer = cusRepo.findByEmail(email);
        return customer != null;   
    }
    
    @Override
    public Customer findCustomerByEmail(String email){
        return cusRepo.findByEmail(email);   
    }
    
    @Override
    public Customer updateCustomer(Customer customer){
        return cusRepo.save(customer);
    }
    
    @Override
    public String generateRandomPassword(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    @Override
    public void updateSearchLastest(Integer id, String keyword){
        Customer customer = cusRepo.findById(id).get();
        customer.setSearchLatest(keyword);
        cusRepo.save(customer);
    }
}
