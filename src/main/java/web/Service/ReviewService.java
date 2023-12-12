
package web.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import web.Model.Product;
import web.Model.Review;

@Service
public interface ReviewService {
    public Float getAverageStarReview(Product product);
    public Review saveReview(Review review);
    public List<Review> getReviewsNotReplied();
}
