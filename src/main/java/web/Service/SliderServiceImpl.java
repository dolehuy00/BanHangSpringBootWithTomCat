
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Slider;
import web.Repository.SliderRepository;

@Service
public class SliderServiceImpl implements SliderService{

    @Autowired private SliderRepository sliderRepo;
    
    @Override
    public List<Slider> getAllSlider() {
        return (List<Slider>) sliderRepo.findAll();
    }
    
}
