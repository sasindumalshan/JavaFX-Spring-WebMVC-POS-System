package lk.interleon.pos.dto;

import lk.interleon.pos.entity.enumretion.ApprovalStatus;
import lk.interleon.pos.entity.enumretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class InventoryDTO {
    private long id;
    private ItemDTO item;
    private LocalDate received_date;
    private int received_qty;
    private ApprovalStatus approval_status;
    private String status;
}
