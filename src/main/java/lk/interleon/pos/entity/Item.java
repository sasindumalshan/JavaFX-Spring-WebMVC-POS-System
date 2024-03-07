package lk.interleon.pos.entity;

import lk.interleon.pos.entity.enumretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@Entity(name = "tbl_master_item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", columnDefinition = "VARCHAR(64)", nullable = false)
    private String code;

    @Column(name = "name", columnDefinition = "VARCHAR(64)", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "category", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToOne(targetEntity = Unit.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "unit", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Unit unit;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, targetEntity = Inventory.class, cascade = CascadeType.ALL)
    private List<Inventory> inventories = new ArrayList<>();

}
