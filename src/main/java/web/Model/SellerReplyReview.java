/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellerreplyreview")
public class SellerReplyReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReplyReviewID")
    private Integer replyReviewID;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "Reply")
    private String reply;

    @Basic(optional = false)
    @Column(name = "reply_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime replyAt;

    @JoinColumn(name = "ReviewID", referencedColumnName = "ReviewID")
    @ManyToOne(optional = false)
    private Review reviewID;
    
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private User userID;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (replyReviewID != null ? replyReviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellerReplyReview)) {
            return false;
        }
        SellerReplyReview other = (SellerReplyReview) object;
        if ((this.replyReviewID == null && other.replyReviewID != null) || (this.replyReviewID != null && !this.replyReviewID.equals(other.replyReviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.SellerReplyReview[ replyReviewID=" + replyReviewID + " ]";
    }
    
}
