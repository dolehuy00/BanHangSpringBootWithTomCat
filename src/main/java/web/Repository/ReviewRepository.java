
package web.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.Product;
import web.Model.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
    
    @Query("SELECT AVG(r.star) FROM Review r WHERE r.productID = ?1")
    public Float getAverageStarReview(Product product);
    
    @Query("SELECT r FROM Review r LEFT JOIN SellerReplyReview s ON s.replyReviewID = r.reviewID WHERE s.reviewID IS NULL")
    public List<Review> getReviewsNotReplied();
}
