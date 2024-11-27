import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../models/RegisterRequest';
import { environment } from '../../environments/environment.development';
import { LoginRequest } from '../models/LoginRequest';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  register(request: RegisterRequest) {
    return this.http.post<string>(`${environment.apiUrl}/register`, request);
  }

  login(request: LoginRequest) {
    return this.http.post<string>(`${environment.apiUrl}/login`, request, { withCredentials: true });
  }


  isAuthenticated() {
    return this.http.get<boolean>(`${environment.apiUrl}/auth/status`, { withCredentials: true });
  }

}
