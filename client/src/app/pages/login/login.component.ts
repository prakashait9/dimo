import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { MustMatch } from '../../_helpers/must-match.validators';
import { LoginService } from './login.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})

export class LoginComponent implements OnInit {
  signupForm: FormGroup;
  loginForm: FormGroup;
  submitted = false;
  loginSubmitted = false;
  loginTab;
  signUpTab;
  loginLink;
  signUpLink;
  test: Date = new Date();
  body;
  loginError = '';
  signupError = '';
  users: any = [];
  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {

    var token = localStorage.getItem("id_token");
    if (token != null) {
      this.router.navigate(['/dashboard']);
    }


    this.loginTab = document.getElementById('logintab');
    this.signUpTab = document.getElementById('signuptab');
    this.loginLink = document.getElementById('pills-login-tab');
    this.signUpLink = document.getElementById('pills-signup-tab');
    this.body = document.getElementsByTagName('body')[0];
    this.body.classList.add('bg-default');
    this.body.classList.remove('grey-background');

    this.signupForm = this.formBuilder.group({
      'firstName': ['',
        [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)]
      ],
      'lastName': ['',
        []
      ],
      email: ['',
        [Validators.required,
        Validators.pattern(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/),
        Validators.maxLength(256)]
      ],
      password: ['',
        [Validators.required,
        Validators.pattern(/^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/),
        Validators.minLength(8),
        Validators.maxLength(30)]
      ],
      confirmPassword: ['',
        Validators.required
      ],
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });

    this.loginForm = this.formBuilder.group({
      email: ['',
        [Validators.required,
        Validators.pattern(/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/),
        Validators.maxLength(256)]
      ],
      password: ['', [
        Validators.required,
        Validators.maxLength(30)]
      ]
    });
  }

  get user() { return this.signupForm.controls; }

  get loginUser() { return this.loginForm.controls; }


  onSubmit() {

    this.signupError = '';
    this.submitted = true;
    if (this.signupForm.invalid) {

      return;
    }
    this.loginService.signupUser(this.signupForm.value)
      //.pipe(first())
      .subscribe(
        data => {
          // set jwt token in local storage
          localStorage.setItem('id_token', data["data"]["token"]);
          // this.alertService.success('Registration successful', true);
          // this.router.navigate(['/login']);
          this.toastr.success('', 'Hurray! Sign up Succesfull!', { timeOut: 3000 });
          this.router.navigate(['/dashboard']);
        },
        error => {
          // this.alertService.error(error);
          //  this.loading = false;
          if (error["status"] == 0) {
            this.signupError = 'Service Unavailable. Please contact site administrator.';
           }
          else if (error["status"] == 401) {
             this.signupError = 'Invalid Email or Password';

          }
          else {
              this.signupError = error["error"]["error_message"];
           }

        });

  }

  onLoginSubmit() {

    this.loginError = "";
    this.loginSubmitted = true;
    if (this.loginForm.invalid) {

      return;
    }

    this.loginService.loginUser(this.loginForm.value)

      .subscribe(
        data => {
          // set jwt token in local storage
          localStorage.setItem('id_token', data["data"]["token"]);
          this.toastr.success('', 'Hurray! Login Succesfull!', { timeOut: 3000 });
          this.router.navigate(['/dashboard']);
        },
        error => {
          if (error["status"] == 0) {
            this.loginError = "Service Unavailable. Please contact site administrator.";
          }
          else if (error["status"] == 401) {
            this.loginError = "Invalid Email or Password.";
          }
          else {
            this.loginError= error["error"]["error_message"];
          }
        });


  } // End of onLoginSubmit

  selectTab(tabName) {


    if (tabName == 'login') {
      this.loginTab.style.display = 'block';
      this.signUpLink.classList.add('active');
      this.signUpTab.style.display = 'none';
      this.loginLink.classList.remove('active');
    }
    else {
      this.loginTab.style.display = 'none';
      this.signUpLink.classList.remove('active');
      this.signUpTab.style.display = 'block';
      this.loginLink.classList.add('active');
    }
    return false;
  }
}
