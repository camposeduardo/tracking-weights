import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../models/RegisterRequest';
import { environment } from '../../environments/environment.development';
import { LoginRequest } from '../models/LoginRequest';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient, private router: Router) { }

  register(request: RegisterRequest) {
    return this.http.post<string>(`${environment.apiUrl}/register`, request, { withCredentials: true });
  }

  login(request: LoginRequest) {
    return this.http.post<string>(`${environment.apiUrl}/login`, request, { withCredentials: true })
  }

  // temporary solution
  isLoggedIn(): boolean | null {
    return !!sessionStorage.getItem("isLogged");
  }

}
