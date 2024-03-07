package lk.interleon.pos.entity;

import lk.interleon.pos.entity.enumretion.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Entity(name = "tbl_master_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "code", columnDefinition = "VARCHAR(64)", nullable = false)
    String code;
    @Column(name = "name", columnDefinition = "VARCHAR(64)", nullable = false)
    String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

}


