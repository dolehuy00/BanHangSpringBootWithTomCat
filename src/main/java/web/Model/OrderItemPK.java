
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
public class OrderItemPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "OrderID")
    private int orderID;
    
    @Basic(optional = false)
    @Column(name = "ProductID")
    private int productID;
    
    @Basic(optional = false)
    @Column(name = "ColorID")
    private int colorID;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderID;
        hash += (int) productID;
        hash += (int) colorID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderItemPK)) {
            return false;
        }
        OrderItemPK other = (OrderItemPK) object;
        if (this.orderID != other.orderID) {
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
        return "web.Model.OrderItemPK[ orderID=" + orderID + ", productID=" + productID + ", colorID=" + colorID + " ]";
    }
    
}
