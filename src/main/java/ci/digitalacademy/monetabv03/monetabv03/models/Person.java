package ci.digitalacademy.monetabv03.monetabv03.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "person_type")
@Table(name = "person")
@ToString
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_personne")
    private Long id_person;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName" , nullable = false)
    private String lastName;

    @Column(name = "numbers" )
    private String numbers;

    @Column(name = "dateOfBirth" )
    private Date dateOfBirth;

    @Column(name = "url_picture")
    private String urlPicture;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adress")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;


}
