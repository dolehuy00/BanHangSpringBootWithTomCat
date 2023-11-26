
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Slider;

@Service
public interface SliderService {
    public List<Slider> getAllSlider();
}
