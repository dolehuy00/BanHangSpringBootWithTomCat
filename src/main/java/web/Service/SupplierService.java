
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Supplier;

@Service
public interface SupplierService {
    public List<Supplier> getAllSupplier();
    public Supplier saveSupplier(Supplier suplier);
    public Supplier findById(Integer supplierId);
    public void lockById(Integer Id);
    public void unlockById(Integer Id);
}
