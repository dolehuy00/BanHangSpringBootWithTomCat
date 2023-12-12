
package web.Service;

import org.springframework.stereotype.Service;
import web.Model.SellerReplyReview;

@Service
public interface SellerReplyReviewService {
    public SellerReplyReview saveReply(SellerReplyReview reply);
}
