
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Color;

@Service
public interface ColorService {
    public List<Color> findAllColor();
}
