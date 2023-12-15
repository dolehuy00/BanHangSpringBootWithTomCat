
package web.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderExcelDTO {
    private String OrderId;
    private String date;
    private String customerName;
    private String phoneNumber;
    private String shippingAddress;
    private String employeeName;
    private List<ProductOrderExcelDTO> productList;
    private String totalAmount;
}
