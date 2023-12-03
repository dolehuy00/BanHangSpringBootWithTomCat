
package web.Service;

import java.math.BigInteger;
import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Customer;

@Service
public interface CartService {
    public Cart getCartByCustomer(Customer customer);
    public BigInteger updateTotalPrice(Integer cartID);
    public Integer updateTotalQuantity(Integer cartID);
    public Cart createEmptyCartForCustomer(Customer customer);
}
