package ci.digitalacademy.monetabv03.monetabv03.service.mapping;

import ci.digitalacademy.monetabv03.monetabv03.models.Absence;
import ci.digitalacademy.monetabv03.monetabv03.models.User;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.UserDTO;

public final class UserMapping {

    private UserMapping() {
    }


    public static void partialUpdate(User user  , UserDTO userDTO){
        if (userDTO.getPseudo()!=null){
            user.setPseudo(userDTO.getPseudo());
        }
        if (userDTO.getPassword()!=null){
            user.setPassword(userDTO.getPassword());
        }
    }

}
