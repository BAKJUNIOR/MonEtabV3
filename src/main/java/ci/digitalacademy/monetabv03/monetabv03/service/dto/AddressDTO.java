package ci.digitalacademy.monetabv03.monetabv03.service.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {

    private Long id_adress;

    private String country;

    private String city;

    private String street;
}
