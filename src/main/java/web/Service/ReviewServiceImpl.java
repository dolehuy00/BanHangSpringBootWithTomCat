
package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.Product;
import web.Model.Review;
import web.Repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired private ReviewRepository reviewRepo;

    @Override
    public Float getAverageStarReview(Product product) {
        return reviewRepo.getAverageStarReview(product);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }
    
    
}
