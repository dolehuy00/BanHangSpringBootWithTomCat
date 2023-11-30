
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Customer;
import web.Model.Orders;

@Service
public interface OrderService {
    public List<Orders> findAllOrder();
    public List<Orders> findOrdersByCustomer(Customer customer);
    public Orders findOrderById(Integer id);
            
}
