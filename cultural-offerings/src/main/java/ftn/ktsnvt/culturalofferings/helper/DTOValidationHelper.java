package ftn.ktsnvt.culturalofferings.helper;

import ftn.ktsnvt.culturalofferings.model.CulturalOffering;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import org.springframework.validation.BindingResult;

public class DTOValidationHelper {
    public static void validateDTO(BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    CulturalOffering.class
            );
    }
}
