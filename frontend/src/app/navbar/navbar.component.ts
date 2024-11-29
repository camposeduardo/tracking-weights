import { Component } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(protected authService: AuthenticationService, private router: Router) { }

  onLogoutButton() {
    this.authService.logout().subscribe(x => {
      this.router.navigate(["/home"]);
      this.authService.setUserAuthencationStatus(false);
    });
  }
}
