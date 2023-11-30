
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Customer;
import web.Model.Orders;
import web.Repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired private OrderRepository orderRepo;
    
    @Override
    public List<Orders> findAllOrder() {
        return (List<Orders>) orderRepo.findAll();
    }

    @Override
    public List<Orders> findOrdersByCustomer(Customer customer) {
        return orderRepo.findOrdersByCustomer(customer);
    }

    @Override
    public Orders findOrderById(Integer id) {
        return orderRepo.findById(id).get();
    }
    
}
