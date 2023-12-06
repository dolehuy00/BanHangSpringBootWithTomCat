
package web.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Color;
import web.Repository.ColorRepository;

@Service
public class ColorServiceImpl implements ColorService{

    @Autowired private ColorRepository colorRepo;
    
    @Override
    public List<Color> findAllColor() {
        return (List<Color>) colorRepo.findAll();
    }

    @Override
    public Color getColorById(Integer id) {
        Optional<Color> colorOptional = colorRepo.findById(id);
        if (colorOptional.isPresent()) {
            return colorOptional.get();
        }
        return null;
    }
}
