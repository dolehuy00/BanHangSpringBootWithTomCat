
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
public class ProductColorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ProductID")
    private int productID;
    
    @Basic(optional = false)
    @Column(name = "ColorID")
    private int colorID;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productID;
        hash += (int) colorID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductColorPK)) {
            return false;
        }
        ProductColorPK other = (ProductColorPK) object;
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
        return "web.Model.ProductColorPK[ productID=" + productID + ", colorID=" + colorID + " ]";
    }
    
}
