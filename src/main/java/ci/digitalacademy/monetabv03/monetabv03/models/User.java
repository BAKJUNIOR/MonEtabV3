package ci.digitalacademy.monetabv03.monetabv03.models;




import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "pseudo" , unique = true , nullable = false)
    private String pseudo;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean active = true;

    @Column(unique = true)
    private String slug ;


    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleUser> roleUser;


    @ManyToOne
    @JoinColumn(name = "id_school")
    private School school;


}
