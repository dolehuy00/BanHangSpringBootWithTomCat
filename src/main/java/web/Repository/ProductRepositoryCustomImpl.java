
package web.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import web.Model.Product;
import web.Model.ProductColor;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Page<Product> searchProduct(String name, List<Integer> suppliers,
            BigInteger lower, BigInteger upper, List<Integer> colors,
            Pageable page, Sort.Order sort) {
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Join<Product, ProductColor> colorJoin = productRoot.join("productColorList");
        
        Predicate predicate = criteriaBuilder.like(productRoot.get("name"), "%" + name + "%");
        
        if (!suppliers.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, productRoot
                    .get("supplier").get("supplierID").in(suppliers));
        }
        
        if (!colors.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, colorJoin.get("color")
                    .get("colorID").in(colors));
        }
        
        if (lower != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.greaterThanOrEqualTo(productRoot.get("price"), lower));
        }
        
        if (upper != null) {
            predicate = criteriaBuilder.and(predicate, 
                    criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), upper));
        }
        
        criteriaQuery.select(productRoot).where(predicate);
        criteriaQuery.groupBy(productRoot);
        if (sort != null) {
            if (sort.getDirection() == Sort.Direction.ASC) {
                criteriaQuery.select(productRoot).orderBy(
                        criteriaBuilder.asc(productRoot.get(sort.getProperty())));
            } else if (sort.getDirection() == Sort.Direction.DESC) {
                criteriaQuery.select(productRoot).orderBy(
                        criteriaBuilder.desc(productRoot.get(sort.getProperty())));
            }
        }
         
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
        
        //Tổng số kết quả
        Integer coutn = query.getResultList().size();
        
        // Đặt vị trí bắt đầu và số lượng kết quả trên mỗi trang
        query.setFirstResult((int) page.getOffset());
        query.setMaxResults(page.getPageSize());

        List<Product> resultList = query.getResultList();

        return new PageImpl<>(resultList, page, coutn);
    }
    
}


