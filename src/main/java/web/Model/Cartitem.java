
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
@Table(name = "cartitems")
public class Cartitem implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected CartitemPK cartitemPK;
    
    @Basic(optional = false)
    @Column(name = "Quantity")
    private int quantity;
    
    @JoinColumn(name = "ColorID", referencedColumnName = "ColorID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Color color;
    
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    
    @JoinColumn(name = "CartID", referencedColumnName = "CartID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cart cart;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartitemPK != null ? cartitemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cartitem)) {
            return false;
        }
        Cartitem other = (Cartitem) object;
        if ((this.cartitemPK == null && other.cartitemPK != null) || (this.cartitemPK != null && !this.cartitemPK.equals(other.cartitemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.Cartitem[ cartitemPK=" + cartitemPK + " ]";
    }
    
}
