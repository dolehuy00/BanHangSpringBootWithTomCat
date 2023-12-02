
package web.Service;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Cartitem;
import web.Model.Customer;
import web.Repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
    
    @Autowired CartRepository cartRepo;
    
    
    @Override
    public Cart getCartByCustomer(Customer customer){
        return cartRepo.findByCustomer(customer);
    }
    
    @Override
    public BigInteger updateTotalPrice(Integer cartID){
        Cart cart = cartRepo.findById(cartID).get();
        BigInteger newTotal = new BigInteger("0"); 
        for (Cartitem cartitem : cart.getCartitemList()) {
            BigInteger quantity = BigInteger.valueOf(cartitem.getQuantity());
            BigInteger price = cartitem.getProduct().getPrice();
            newTotal = newTotal.add(quantity.multiply(price));
        }
        cart.setTotalPrice(newTotal);
        cartRepo.save(cart);
        return newTotal;
    }
}
