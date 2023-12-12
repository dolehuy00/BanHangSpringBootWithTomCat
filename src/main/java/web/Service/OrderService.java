
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Customer;
import web.Model.Orders;
import web.Model.User;

@Service
public interface OrderService {
    public List<Orders> findAllOrder();
    public List<Orders> findOrdersByCustomer(Customer customer);
    public Orders findOrderById(Integer id);
    public Orders saveOrder(Orders order);    
    public List<Orders> findOrdersAwaiting(); 
    public List<Orders> findOrdersByUser(User user);
    public List<Orders> findOrdersConfirmed(Integer userId); 
    public List<Orders> findOrdersDelivering(Integer userId);
    public List<Orders> findOrdersFinished(Integer userId);
}
