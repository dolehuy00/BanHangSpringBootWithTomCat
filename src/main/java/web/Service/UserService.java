
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.User;

@Service
public interface UserService {
    public List<User> findAllUser();
}
