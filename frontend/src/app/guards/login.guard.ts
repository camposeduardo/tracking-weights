import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map, catchError } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';

export const loginGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthenticationService);
  const router = inject(Router);
  return authService.isAuthenticated().pipe(
    map((status) => {
      if (status) {
        router.navigate(['/app']);
        return false;
      } else {
        return true;
      }
    }),
    catchError(() => {
      router.navigate(['/home']);
      return [false];
    })
  );
};
