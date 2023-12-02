
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.CartitemPK;

@Service
public interface CartItemService {
    public void updateQuantityCartItem(CartitemPK id, Integer quantity);
}
