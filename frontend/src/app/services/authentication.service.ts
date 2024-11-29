import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterRequest } from '../models/RegisterRequest';
import { environment } from '../../environments/environment.development';
import { LoginRequest } from '../models/LoginRequest';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { map } from 'rxjs/internal/operators/map';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private userAuthenticationStatusSubject = new BehaviorSubject<boolean>(false);

  public userAuthenticationStatus$ = this.userAuthenticationStatusSubject.asObservable();

  constructor(private http: HttpClient) { }

  register(request: RegisterRequest) {
    return this.http.post<string>(`${environment.apiUrl}/register`, request);
  }

  login(request: LoginRequest) {
    return this.http.post<string>(`${environment.apiUrl}/login`, request, { withCredentials: true });
  }

  logout() {
    return this.http.post<any>(`${environment.apiUrl}/logout`, null, { withCredentials: true });
  }

  isAuthenticated() {
    return this.http.get<boolean>(`${environment.apiUrl}/auth/status`, { withCredentials: true }).pipe(
      map((status) => {
        this.setUserAuthencationStatus(status);
        return status;
      }));
  }

  setUserAuthencationStatus(isLogged: boolean) {
    this.userAuthenticationStatusSubject.next(isLogged);
  }

}
