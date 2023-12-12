
package web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.Model.SellerReplyReview;
import web.Repository.SellerReplyReviewRepository;

@Service
public class SellerReplyReviewServiceImpl implements SellerReplyReviewService{
    @Autowired private SellerReplyReviewRepository replyRepo;

    @Override
    public SellerReplyReview saveReply(SellerReplyReview reply) {
        return replyRepo.save(reply);
    }

}
