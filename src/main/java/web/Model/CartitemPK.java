
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartitemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CartID")
    private int cartID;
    
    @Basic(optional = false)
    @Column(name = "ProductID")
    private int productID;
    
    @Basic(optional = false)
    @Column(name = "ColorID")
    private int colorID;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cartID;
        hash += (int) productID;
        hash += (int) colorID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartitemPK)) {
            return false;
        }
        CartitemPK other = (CartitemPK) object;
        if (this.cartID != other.cartID) {
            return false;
        }
        if (this.productID != other.productID) {
            return false;
        }
        if (this.colorID != other.colorID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.CartitemPK[ cartID=" + cartID + ", productID=" + productID + ", colorID=" + colorID + " ]";
    }
    
}
