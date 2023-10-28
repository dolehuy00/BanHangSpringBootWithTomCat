
package web.Model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "colors")
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ColorID")
    private Integer colorID;
    
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private List<OrderItem> orderItemList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private List<ProductColor> productColorList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private List<Cartitem> cartitemList;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colorID != null ? colorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.colorID == null && other.colorID != null) || (this.colorID != null && !this.colorID.equals(other.colorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Model.Color[ colorID=" + colorID + " ]";
    }
    
}
