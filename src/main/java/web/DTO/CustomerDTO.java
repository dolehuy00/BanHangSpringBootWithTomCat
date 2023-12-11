
package web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer status;
}
