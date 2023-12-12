
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productcolors")
public class ProductColor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ProductColorPK productColorPK;
    
    @Basic(optional = false)
    @Column(name = "Quantity")
    private int quantity;
    
    @Basic(optional = false)
    @Column(name = "Images")
    private String images;
    
    @JoinColumn(name = "Status", referencedColumnName = "StatusID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Status status;
    
    @JoinColumn(name = "ColorID", referencedColumnName = "ColorID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Color color;
    
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productColorPK != null ? productColorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProductColor)) {
            return false;
        }
        ProductColor other = (ProductColor) object;
        if ((this.productColorPK == null && other.productColorPK != null) || (this.productColorPK != null && !this.productColorPK.equals(other.productColorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.ProductColor[ productColorPK=" + productColorPK + " ]";
    }
    
}
