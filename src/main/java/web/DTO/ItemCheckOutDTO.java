
package web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCheckOutDTO {
    private Integer product;
    private Integer color;
    private Integer quantity;
    @Override
    public String toString(){
        return "['product': "+this.product+", 'color': "+this.color+", 'quantity': "+this.quantity+"]";
    }
}
