
package web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String username;
    private String password;
    private String passwordConfirm;
    private String email;
    private String phoneNumber;
    private String address;
    private Integer role;
    private Integer status;
}
