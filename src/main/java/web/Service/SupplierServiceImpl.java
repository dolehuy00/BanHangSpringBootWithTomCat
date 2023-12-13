
package web.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Supplier saveSupplier(Supplier suplier) {
        return supplierRepo.save(suplier);
    }

    @Override
    public Supplier findById(Integer supplierId) {
        Optional<Supplier> supplier = supplierRepo.findById(supplierId);
        if (supplier.isPresent()) {
            return supplier.get();
        }
        return null;
    }

    @Override
    public void lockById(Integer Id) {
        supplierRepo.lockById(Id);
    }

    @Override
    public void unlockById(Integer Id) {
        supplierRepo.unlockById(Id);
    }

    @Override
    public List<Supplier> searchInManage(String keyword) {
        return supplierRepo.searchInManage("%"+keyword+"%");
    }
    
}
