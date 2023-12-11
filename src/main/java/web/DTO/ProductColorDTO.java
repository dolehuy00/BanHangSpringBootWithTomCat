
package web.DTO;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorDTO {
    private Integer color;
    private Integer quantity;
    @Lob
    private String image;
    
}
