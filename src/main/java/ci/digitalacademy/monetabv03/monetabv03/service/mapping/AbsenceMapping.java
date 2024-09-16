package ci.digitalacademy.monetabv03.monetabv03.service.mapping;

import ci.digitalacademy.monetabv03.monetabv03.models.Absence;
import ci.digitalacademy.monetabv03.monetabv03.service.dto.AbsenceDTO;

public final class AbsenceMapping {


    private AbsenceMapping(){
    }

    public static void partialUpdate(Absence absence, AbsenceDTO absenceDTO){
        if (absenceDTO.getNumberAbsence()!=null){
            absence.setNumberAbsence(absenceDTO.getNumberAbsence());
        }
        if (absenceDTO.getDateAbsence()!=null){
            absence.setDateAbsence(absenceDTO.getDateAbsence());
 }
}

}
