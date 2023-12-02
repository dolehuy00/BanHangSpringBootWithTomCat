
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Customer;

@Service
public interface CartService {
    public Cart getCartByCustomer(Customer customer);
}
