
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.Cartitem;
import web.Model.CartitemPK;

@Service
public interface CartItemService {
    public void updateQuantityCartItem(CartitemPK id, Integer quantity);
    public boolean deleteProductCartItem(CartitemPK id);
    public Cartitem addCartItem(Cartitem cartItem);
    public Cartitem getCartItemById(CartitemPK id);
}
