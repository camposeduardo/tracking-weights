import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { map } from 'rxjs/internal/operators/map';
import { catchError } from 'rxjs/internal/operators/catchError';

export const authGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthenticationService);
  const router = inject(Router);

  return authService.isAuthenticated().pipe(
    map((status) => {
        if (status) {
            return true;
        } else {
            router.navigate(['/login']);
            return false;
        }
    }),
    catchError(() => {
        router.navigate(['/login']);
        return [false];
    })
  );

};
