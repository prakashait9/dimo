import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  signupUser(user: User) {
    return this.http.post(`http://localhost:8080/api/signup`, user);
  }

  loginUser(user: User) {
    return this.http.post(`http://localhost:8080/api/login`, user);
  }
}
