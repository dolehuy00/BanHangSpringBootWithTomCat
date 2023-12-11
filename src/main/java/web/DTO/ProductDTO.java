
package web.DTO;

import java.math.BigInteger;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private BigInteger price;
    private String description;
    private String specifications;
    private List<ProductColorDTO> colorList;
    private Integer status;
    private Integer supplier;
}
