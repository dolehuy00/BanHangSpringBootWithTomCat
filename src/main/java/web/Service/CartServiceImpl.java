
package web.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        if(cart.getCartitemList() != null){
            Integer quantity = cart.getCartitemList().size();
            cart.setTotalQuantity(quantity);
            cartRepo.save(cart);
            return quantity;
        }
        return 0;
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

    @Override
    public List<Cart> findAllCart() {
        return cartRepo.findAll();
    }

    @Override
    public Cart getCartById(Integer cartId) {
        Optional<Cart> optional = cartRepo.findById(cartId);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Cart> searchInManage(String keyword) {
        return cartRepo.searchInManage("%"+keyword+"%");
    }
 
}
