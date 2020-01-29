import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { MustMatch } from '../../_helpers/must-match.validators';
import { LoginService } from './login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})

export class LoginComponent implements OnInit {
  signupForm: FormGroup;
  loginForm: FormGroup;
  submitted = false;
  loginTab;
  signUpTab;
  loginLink;
  signUpLink;

  users: any = [];
  constructor(private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.loginTab = document.getElementById('logintab');
    this.signUpTab = document.getElementById('signuptab');
    this.loginLink = document.getElementById('pills-login-tab');
    this.signUpLink = document.getElementById('pills-signup-tab');

    this.signupForm = this.formBuilder.group({
      'firstName': ['',
        [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)]
      ],
      'lastName': ['',
        [Validators.required,
        Validators.minLength(2),
        Validators.maxLength(50)]
      ],
      email: ['',
        [Validators.required,
        Validators.email]
      ],
      password: ['',
        [Validators.required,
        Validators.minLength(6)]
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
        Validators.email]
      ],
      password: ['', [
        Validators.required]
      ]
    });


  }

  get user() { return this.signupForm.controls; }


  onSubmit() {
    this.submitted = true;
    if (this.signupForm.invalid) {

      return;
    }

    this.loginService.signupUser(this.signupForm.value)
      //.pipe(first())
      .subscribe(
        data => {
          // this.alertService.success('Registration successful', true);
          // this.router.navigate(['/login']);
          console.log("Success");
          this.router.navigate(['/dashboard']);
        },
        error => {
          // this.alertService.error(error);
          //  this.loading = false;
          console.log("error");
          this.router.navigate(['/dashboard']);
        });


    //alert("Success!" + this.signupForm.value);
  }

  selectTab(tabName) {
    console.log(tabName);
    if (tabName == 'login') {
      this.loginTab.style.display = 'block';
      this.loginLink.classList.add('active');
      this.signUpTab.style.display = 'none';
      this.signUpLink.classList.remove('active');
    }
    else {
      this.loginTab.style.display = 'none';
      this.loginLink.classList.remove('active');
      this.signUpTab.style.display = 'block';
      this.signUpLink.classList.add('active');
    }
    return false;
  }
}
