
package web.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Supplier;
import web.Repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierRepository supplierRepo;
    
    @Override
    public List<Supplier> getAllSupplier() {
        return (List<Supplier>)supplierRepo.findAll();
    }
    
}
