
package web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderExcelDTO {
    private String name;
    private String quantity;
    private String price;
    private String totalPrice;
}
