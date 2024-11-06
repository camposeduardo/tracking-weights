import { CommonModule } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { ErrorMessage } from '../../models/ErrorMessage';

@Component({
  selector: 'login-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {

  signInForm!: FormGroup;
  registerForm!: FormGroup;

  loginErrorMessage: string | null = null;
  registerErrorMessage: string | null = null;

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.registerForm = new FormGroup({
      username: new FormControl(null, [Validators.required]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
    });

    this.signInForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
    });
  }

  onSubmitSignIn() {
    this.authService.login(this.signInForm.value).subscribe({
      next: (data) => {
        // temporary solution
        sessionStorage.setItem("isLogged", "true");
        this.router.navigate(['/home']);

      }, error: (error: ErrorMessage) => {
        this.loginErrorMessage = error.message;
      }
    });;
  }

  onCreateAccount() {
    this.authService.register(this.registerForm.value).subscribe({
      next: () => {
        document.getElementById('closeModal')!.click();
        this.registerForm.reset();
      },

      error: (error: ErrorMessage) => {
        this.registerErrorMessage = error.message;
      }
    });
  }

  closeAlert(alert: string) {
    alert === 'login' ? this.loginErrorMessage = null : this.registerErrorMessage = null
  }

}
