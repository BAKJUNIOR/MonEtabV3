package ci.digitalacademy.monetabv03.monetabv03.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotBlank(message = "Le nom de famille est obligatoire")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "numbers")
    private String numbers;

    @NotNull(message = "La date de naissance est obligatoire")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "url_picture")
    private String urlPicture;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le genre est obligatoire")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adress")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;


}
