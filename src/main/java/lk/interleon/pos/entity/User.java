package lk.interleon.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

@Entity
@Table(name = "tbl_master_user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", columnDefinition = "VARCHAR(64)", nullable = false)
    private String user_name;
    @Column(name = "password", columnDefinition = "VARCHAR(64)", nullable = false)
    private String password;
    @Column(name = "mail", columnDefinition = "VARCHAR(64)", nullable = false)
    private String mail;
    @Column(name = "temp_password")
    private String temp_password;
}
