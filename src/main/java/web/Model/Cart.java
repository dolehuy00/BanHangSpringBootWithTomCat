
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CartID")
    private Integer cartID;
    
    @Basic(optional = false)
    @Column(name = "total_price")
    private BigInteger totalPrice;
    
    @Basic(optional = false)
    @Column(name = "total_quantity")
    private int totalQuantity;
    
    @Basic(optional = false)
    @Column(name = "create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createAt;
    
    @Basic(optional = false)
    @Column(name = "update_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;
    
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @OneToOne(optional = false)
    private Customer customer;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<Cartitem> cartitemList;

    public Cart(Integer cartID) {
        this.cartID = cartID;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartID != null ? cartID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.cartID == null && other.cartID != null) || (this.cartID != null && !this.cartID.equals(other.cartID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.Cart[ cartID=" + cartID + " ]";
    }
    
}
