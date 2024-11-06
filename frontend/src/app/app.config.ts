import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { AuthenticationService } from './services/authentication.service';
import { HttpClientModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { errorInterceptor } from './interceptors/error.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
              importProvidersFrom(HttpClientModule),
              provideHttpClient(withInterceptors([errorInterceptor])) ]
};
