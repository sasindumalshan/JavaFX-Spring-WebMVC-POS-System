package lk.interleon.pos.dto;

import lk.interleon.pos.entity.Category;
import lk.interleon.pos.entity.Unit;
import lk.interleon.pos.entity.enumretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDTO {

    private long id;
    private String code;
    private String name;
    private String category;
    private String unit;
    private Status status;

}
