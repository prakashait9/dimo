import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

import { LoginComponent } from './login.component';
import {ToastrModule } from 'ngx-toastr';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, RouterTestingModule.withRoutes([]), HttpClientModule, ToastrModule.forRoot()],
      declarations: [LoginComponent],
      providers: []
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should throw error for required elements', function() {
    component.onSubmit();
    expect(component.user.firstName.errors.required).toBe(true);
    expect(component.user.email.errors.required).toBe(true);
    expect(component.user.password.errors.required).toBe(true);
    expect(component.user.confirmPassword.errors.required).toBe(true);
  });

  it('should throw errors for minimum length of characters', function() {
    component.user.firstName.setValue("S");
    component.user.lastName.setValue("P");
    component.user.password.setValue("S");

    component.onSubmit();
    expect(component.user.firstName.errors.minlength.actualLength).toBe(1);
    expect(component.user.password.errors.minlength.actualLength).toBe(1);
  });

  it('password should match confirmPassword', function() {
    component.user.password.setValue("S123456");
    component.user.confirmPassword.setValue("S1234567");
    component.onSubmit();
    expect(component.user.confirmPassword.errors.mustMatch).toBe(true);
  });

  it('should throw an error for invalid email', function() {
    component.loginUser.email.setValue("abcgmail.com");
    component.onSubmit();
    expect(component.loginUser.email.hasError('pattern')).toBe(true);
  });


  it("Should throw error if email & password is not provided",function(){

      component.loginUser.email.setValue("");
      // component.loginUser.password.setValue("");
      component.onLoginSubmit();

      expect(component.loginUser.password.errors.required).toBe(true);
  });


  it(" should allow to login when email & password is provided", function(){

          component.loginUser.email.setValue("abc@gmail.com");
          component.loginUser.password.setValue("password123");
          component.onLoginSubmit();

           expect(component.loginUser.password.errors).toBe(null);
           expect(component.loginUser.email.errors).toBe(null);
  });

});
