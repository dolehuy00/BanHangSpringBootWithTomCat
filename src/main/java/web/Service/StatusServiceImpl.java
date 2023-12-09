
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Status;
import web.Repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService{
    @Autowired private StatusRepository statusRepo;

    @Override
    public Status getStatusById(Integer id) {
        return statusRepo.findById(id).get();
    }

    @Override
    public List<Status> findAll() {
        return (List<Status>) statusRepo.findAll();
    }
}
