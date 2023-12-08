
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Cart;
import web.Model.Cartitem;
import web.Model.CartitemPK;

@Service
public interface CartItemService {
    public void updateQuantityCartItem(CartitemPK id, Integer quantity);
    public boolean deleteProductCartItem(CartitemPK id);
    public Cartitem addCartItem(Cartitem cartItem);
    public Cartitem getCartItemById(CartitemPK id);
    public void deleteListCartItemById(List<CartitemPK> cartItems);
    public void emptyCartById(Integer cartId);
}
