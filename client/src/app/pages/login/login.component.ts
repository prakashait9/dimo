import {Component, OnInit}from '@angular/core';
import {FormGroup, FormControl , Validators}from '@angular/forms';


@Component({
selector: 'app-login',
templateUrl: './login.component.html',
styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

firstName: string;
lastName: string;
email: string;
password: string;
confirmPassword: string;

signupForm ;

constructor() {


   }

 ngOnInit(): void {



   this.signupForm = new FormGroup({
     'firstName': new FormControl(this.firstName, [
       Validators.required,
       Validators.minLength(2),
        Validators.maxLength(50)
     ]),
     'lastName': new FormControl(this.lastName, [
            Validators.required,
            Validators.minLength(2),
             Validators.maxLength(50)
          ]),
          'email': new FormControl(this.email, [
                 Validators.required

               ]),
               'password': new FormControl(this.password, [
                      Validators.required,
                      Validators.minLength(8),
                       Validators.maxLength(32)
                    ]),
                    'confirmPassword': new FormControl(this.confirmPassword, [
                           Validators.required,
                           Validators.minLength(8),
                            Validators.maxLength(32)
                         ]),
   });

 }



//   signupForm = new FormGroup({
//     firstName: new FormControl(''),
//     lastName: new FormControl(''),
//     email: new FormControl(''),
//      password: new FormControl(''),
//         confirmPassword: new FormControl('')
//
//   });

  onSubmit() {
    // TODO: Use EventEmitter with form value
   // alert(this.signupForm.value);
  }

  validateForm() {
    alert("working");



  }

}
