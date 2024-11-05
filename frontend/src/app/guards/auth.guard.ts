import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthenticationService);
  const router = inject(Router);

  // temporary solution
  if (authService.isLoggedIn()) {
    router.navigate(['/home']);
    return false;
  }
  return true;

};
