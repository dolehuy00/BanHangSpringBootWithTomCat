
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Role;
import web.Repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired RoleRepository roleRepo;
    
    
    @Override
    public List<Role> findAllExceptAdmin() {
        return (List<Role>) roleRepo.findAllRoleExceptAdmin();
    } 
    
}
