import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ReactiveFormsModule],
      declarations: [LoginComponent]
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
    expect(component.user.lastName.errors.required).toBe(true);
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
    expect(component.user.lastName.errors.minlength.actualLength).toBe(1);
    expect(component.user.password.errors.minlength.actualLength).toBe(1);
  });

  it('password should match confirmPassword',function(){
      component.user.password.setValue("S123456");
      component.user.confirmPassword.setValue("S1234567");
      component.onSubmit();
      expect(component.user.confirmPassword.errors.mustMatch).toBe(true);
  });

  it('should throw an error for invalid email',function(){
      component.user.email.setValue("abcgmail.com");
      component.onSubmit();
      expect(component.user.email.errors.email).toBe(true);
  });

});
