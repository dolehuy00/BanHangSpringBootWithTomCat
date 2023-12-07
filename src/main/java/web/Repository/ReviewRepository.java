
package web.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Product;
import web.Model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
    
    @Query("SELECT AVG(r.star) FROM Review r WHERE r.productID = ?1")
    public Float getAverageStarReview(Product product);
}
