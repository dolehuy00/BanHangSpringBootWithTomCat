
package web.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.User;
import web.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public List<User> findAllUser() {
        return (List<User>) userRepo.findAll();
    }
    
    @Override
    public User findUserByUserName(String username) {
        try{
            User user = userRepo.findByUsername(username);
            return user;
        }catch(Exception e){
            return null;
        }
    }
    
    @Override
    public User isValidAccount(String username, String password) {
        User user = userRepo.findByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }  
    }

    @Override
    public void lockUserById(Integer userId) {
        userRepo.lockUserById(userId);
    }

    @Override
    public List<User> findAllUserExceptAdmin() {
        return userRepo.findAllUserExceptAdmin();
    }

    @Override
    public User findUserById(Integer userID) {
        Optional<User> user = userRepo.findById(userID);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkExitsAccoutByUsername(String username) {
        return userRepo.findByUsername(username) != null;
    }

    @Override
    public boolean checkExitsAccoutByEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public boolean checkExitsAccoutByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public void unlockUserById(Integer userId) {
        userRepo.unlockUserById(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> searchInManage(String keyword) {
        return userRepo.searchInManage("%"+keyword+"%");
    }
}
