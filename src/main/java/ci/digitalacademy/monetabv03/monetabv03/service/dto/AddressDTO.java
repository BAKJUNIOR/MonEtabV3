package ci.digitalacademy.monetabv03.monetabv03.service.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id_adress;

    private String country;

    private String city;

    private String street;


}
