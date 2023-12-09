
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Role;

@Service
public interface RoleService {
    public List<Role> findAllExceptAdmin();
}
