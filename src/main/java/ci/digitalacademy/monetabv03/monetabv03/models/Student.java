package ci.digitalacademy.monetabv03.monetabv03.models;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "Student")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "student")
@ToString
public class Student extends Person{

    @Column(unique=true , name = "matricule")
    private String matricule;

    @OneToMany(  mappedBy = "student")
    @Column(nullable = true)
    private List<Absence> absence;

    @Column(name = "phone_number_parent" )
    private String phoneNumberParent;


}
