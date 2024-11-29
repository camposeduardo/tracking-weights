import { Component } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private authService: AuthenticationService, private router: Router) { }

  onLogoutButton() {
    this.authService.logout().subscribe(x => {
      this.router.navigate(["/home"])
    });
  }
}
