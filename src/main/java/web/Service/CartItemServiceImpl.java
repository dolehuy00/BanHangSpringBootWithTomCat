
package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Cartitem;
import web.Model.CartitemPK;
import web.Repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired CartItemRepository cartItemRepo;
     
    @Override
    public void updateQuantityCartItem(CartitemPK id, Integer quantity) {
        Cartitem cartItem = cartItemRepo.findById(id).get();
        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);
    }
    
}
