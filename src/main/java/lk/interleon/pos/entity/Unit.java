package lk.interleon.pos.entity;

import lk.interleon.pos.entity.enumretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/3/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@Entity
public class Unit {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(64)")
    String id;
    @Column(name = "code", columnDefinition = "VARCHAR(64)")
    String code;
    @Column(name = "name", columnDefinition = "VARCHAR(64)")
    String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
