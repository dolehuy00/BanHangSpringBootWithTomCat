
package web.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.Model.SellerReplyReview;

@Repository
public interface SellerReplyReviewRepository extends CrudRepository<SellerReplyReview, Integer>{
    
}
