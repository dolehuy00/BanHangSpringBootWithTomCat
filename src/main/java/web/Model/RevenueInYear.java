
package web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RevenueInYear {
    @Id
    private Integer month;
    private BigInteger total;
}
