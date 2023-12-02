
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;
    
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    
    @Column(name = "SearchLatest")
    @Lob
    private String searchLatest;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Cart cart;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerID")
    private List<Orders> ordersList;
    
    @JoinColumn(name = "Status", referencedColumnName = "StatusID")
    @ManyToOne(optional = false)
    private Status status;

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerID == null && other.customerID != null) || (this.customerID != null && !this.customerID.equals(other.customerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.Customer[ customerID=" + customerID + " ]";
    }
    
}
