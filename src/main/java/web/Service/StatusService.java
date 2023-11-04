
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.Status;

@Service
public interface StatusService {
    public Status getStatusById(Integer id);
}
