
package web.Repository;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import web.Model.Product;

@Repository
public interface ProductRepositoryCustom {
    public Page<Product> searchProduct(String name, List<Integer> suppliers,
            BigInteger lower, BigInteger upper, List<Integer> colors,
            Pageable page, Sort.Order sort);
}
