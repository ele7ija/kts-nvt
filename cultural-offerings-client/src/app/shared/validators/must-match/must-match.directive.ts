import { Directive } from '@angular/core';
import { AbstractControl, FormGroup, NG_VALIDATORS, ValidationErrors, Validator, ValidatorFn } from '@angular/forms';


// value from password field must match value from confirmedPassword
export const mustMatchValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
  const password = control.get('passField');
  const confirmedPassword = control.get('confPassField');

  if (confirmedPassword.errors && !confirmedPassword.errors.mustMatch) {
    // return if another validator has already found an error on the matching control
    return;
  }

  // set error on matching control if validation fails
  if (password.value !== confirmedPassword.value) {
    confirmedPassword.setErrors({ mustMatch: true });
  } else {
    confirmedPassword.setErrors(null);
  }

};

// For a template-driven form, you must create a directive to wrap the validator function.
@Directive({
  selector: '[appMustMatch]',
  providers: [{ provide: NG_VALIDATORS, useExisting: MustMatchDirective, multi: true }]
})

export class MustMatchDirective implements Validator {

  constructor() { }

  validate(control: AbstractControl): ValidationErrors {
    return mustMatchValidator(control);
  }

}
