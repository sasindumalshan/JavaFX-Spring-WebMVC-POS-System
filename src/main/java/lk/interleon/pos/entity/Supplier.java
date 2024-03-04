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
public class Supplier {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(64)")
    String id;
    @Column(name = "code", columnDefinition = "VARCHAR(64)")
    String code;
    @Column(name = "name", columnDefinition = "VARCHAR(64)")
    String name;
    @Column(name = "address")
    String address;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Unit> products = new ArrayList<>();

    /*@ManyToMany
    @JoinTable(
            name = "supplier_unit",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private List<Unit> units = new ArrayList<>();*/
}
