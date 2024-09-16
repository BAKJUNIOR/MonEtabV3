package ci.digitalacademy.monetabv03.monetabv03.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "absence")
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_absence;

    @Column(name = "date_absence" , nullable = false)
    private Date dateAbsence;

    @Column(name = "number_absence" , nullable = false)
    private Integer numberAbsence;

    @Column(name = "slug" , unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Student student;
}
