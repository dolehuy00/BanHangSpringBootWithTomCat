
package web.Controller;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Model.Review;
import web.Model.SellerReplyReview;
import web.Model.User;
import web.Service.ReviewService;
import web.Service.SellerReplyReviewService;

@Controller
public class ManageReviewController {
    
    @Autowired private ReviewService reviewServ;
    @Autowired private HttpSession session;
    @Autowired private SellerReplyReviewService replyServ;
    
    
    @GetMapping("admin/review-management")
    public String ViewReviewNotReplied(Model model){
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListReview", reviewServ.getReviewsNotReplied());
            return "review/view-manage-review";
        }   
    }
    
    @PostMapping("product/reply-review")
    @ResponseBody
    public String ReplyReview(@RequestParam("reply") String reply,
                    @RequestParam("review") Integer reviewId){
        JSONObject response = new JSONObject();
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            response.put("message", "Bạn không có quyền truy cập");
            return response.toString();
        } else if (user.getRole().getRoleID() != 1 &&
                   user.getRole().getRoleID() != 2) {
            response.put("message", "Bạn không có quyền thực hiện");
            return response.toString();
        } else {
            SellerReplyReview replyReview = new SellerReplyReview();
            replyReview.setUserID(user);
            replyReview.setReply(reply);
            replyReview.setReviewID(new Review(reviewId));
            replyReview.setReplyAt(LocalDateTime.now());
            SellerReplyReview replyReviewSaved = replyServ.saveReply(replyReview);
            if(replyReviewSaved != null){
                response.put("success", true);
            }else{
                response.put("success", false);
            }
            return response.toString();
        }
    }
    
}
