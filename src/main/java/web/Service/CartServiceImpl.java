
package web.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
    
    @Override
    public Integer updateTotalQuantity(Integer cartID){
        Cart cart = cartRepo.findById(cartID).get();
        Integer quantity = cart.getCartitemList().size();
        cart.setTotalQuantity(quantity);
        return quantity;
    }    

    @Override
    public Cart createEmptyCartForCustomer(Customer customer) {
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        newCart.setCreateAt(LocalDateTime.now());
        newCart.setUpdateAt(LocalDateTime.now());
        newCart.setTotalPrice(new BigInteger("0"));
        newCart.setTotalQuantity(0);
        return cartRepo.save(newCart);   
    }
}
