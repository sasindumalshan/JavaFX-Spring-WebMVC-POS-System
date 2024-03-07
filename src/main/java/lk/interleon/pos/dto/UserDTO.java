package lk.interleon.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private Long id;
    private String user_name;
    private String password;
    private String mail;
}
