import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let errorMessage = '';

        if (error.error instanceof ErrorEvent) {
          errorMessage = `${error.error.message}`;
        } else {
          if (error.error && error.error.message) {
            errorMessage = `${error.error.message}`;
          } else {
            errorMessage = `${error.status}: ${error.message}`;
          }
        }

        return throwError(() => new Error(errorMessage));
    })
  );
};
