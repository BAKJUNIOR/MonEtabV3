package ci.digitalacademy.monetabv03.monetabv03.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "teacher")
@ToString
public class Teacher extends Person {

    private Boolean available;

    private String specialty;


}
