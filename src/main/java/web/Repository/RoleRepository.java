
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
    
    @Query("FROM Role r WHERE r.roleID != 1")
    public List<Role> findAllRoleExceptAdmin();
}
