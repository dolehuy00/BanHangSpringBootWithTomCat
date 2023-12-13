
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaRepository<User, Integer> {
    
    public User findByUsername(String username);
    public User findByPhoneNumber(String phoneNumber);
    public User findByEmail(String email);
    
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status.statusID = 0 WHERE u.userID = ?1 AND u.role.roleID != 1")
    public void lockUserById(Integer userId);
    
    @Query("FROM User u WHERE u.role.roleID != 1")
    public List<User> findAllUserExceptAdmin();

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status.statusID = 1 WHERE u.userID = ?1")
    public void unlockUserById(Integer userId);
    
    
    @Query("FROM User u WHERE (u.name LIKE ?1"
            + " OR u.userID LIKE ?1"
            + " OR u.username LIKE ?1"
            + " OR u.phoneNumber LIKE ?1"
            + " OR u.role.name LIKE ?1"
            + " OR u.status.name LIKE ?1"
            + " OR u.email LIKE ?1)"
            + " AND u.role.roleID != 1")
    public List<User> searchInManage(String keyword);
}
