
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Customer;
import web.Model.Orders;
import web.Model.User;
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

    @Override
    public Orders saveOrder(Orders order) {
        return orderRepo.save(order);
    }

    @Override
    public List<Orders> findOrdersAwaiting() {
        return orderRepo.findOrdersAwaiting();
    }

    @Override
    public List<Orders> findOrdersByUser(User user) {
        return orderRepo.findOrdersByUser(user);
    }

    @Override
    public List<Orders> findOrdersConfirmed(Integer userId) {
        return orderRepo.findOrdersConfirmed(userId);
    }

    @Override
    public List<Orders> findOrdersDelivering(Integer userId) {
        return orderRepo.findOrdersDelivering(userId);
    }

    @Override
    public List<Orders> findOrdersFinished(Integer userId) {
        return orderRepo.findOrdersFinished(userId);
    } 

    @Override
    public List<Orders> searchInManage(String keyword) {
        return orderRepo.searchInManage("%"+keyword+"%");
    }
}
