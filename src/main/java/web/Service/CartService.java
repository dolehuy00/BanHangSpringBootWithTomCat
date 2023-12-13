
package web.Service;

import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Customer;

@Service
public interface CartService {
    public Cart getCartByCustomer(Customer customer);
    public BigInteger updateTotalPrice(Integer cartID);
    public Integer updateTotalQuantity(Integer cartID);
    public Cart createEmptyCartForCustomer(Customer customer);
    public List<Cart> findAllCart();
    public Cart getCartById(Integer cartId);
    public List<Cart> searchInManage(String keyword);
}
