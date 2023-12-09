
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Status;

@Service
public interface StatusService {
    public Status getStatusById(Integer id);
    public List<Status> findAll();
}
