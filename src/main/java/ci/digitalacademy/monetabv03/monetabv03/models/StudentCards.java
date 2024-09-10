package ci.digitalacademy.monetabv03.monetabv03.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student_cards")
@ToString
public class StudentCards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudentCard;

    @Column(nullable = false , name = "reference")
    private String reference;

    @Column(nullable = false , name = "issue_date")
    private Date issueDate;

    @Column(nullable = false , name = "expiry_date")
    private Date expiryDate;

    @ManyToOne
    private Student student;
}
