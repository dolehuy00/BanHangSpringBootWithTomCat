
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductID")
    private Integer productID;
    
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "Price")
    private BigInteger price;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "Description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderItem> orderItemList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductColor> productColorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Cartitem> cartitemList;
    
    @JoinColumn(name = "Status", referencedColumnName = "StatusID")
    @ManyToOne(optional = false)
    private Status status;
    
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
    @ManyToOne(optional = false)
    private Supplier supplierID;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.Product[ productID=" + productID + " ]";
    }
    
}