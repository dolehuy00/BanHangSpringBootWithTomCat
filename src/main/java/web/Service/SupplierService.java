
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Supplier;

@Service
public interface SupplierService {
    public List<Supplier> getAllSupplier();
}
