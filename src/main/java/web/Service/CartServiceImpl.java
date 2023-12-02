
package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Customer;
import web.Repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
    
    @Autowired CartRepository cartRepo;
    
    
    @Override
    public Cart getCartByCustomer(Customer customer){
        return cartRepo.findByCustomer(customer);
    }
}
