package lk.interleon.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Sasindu Malshan
 * @project FL
 * @date 3/4/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UnitDTO {
    String id;
    String code;
    String name;
    String status;
    String supplierID;
    String categoryID;
}
