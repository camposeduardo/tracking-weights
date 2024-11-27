import { Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { MainPageComponent } from './main-page/main-page.component';
import { authGuard } from './guards/auth.guard';
import { loginGuard } from './guards/login.guard';

export const routes: Routes = [

  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },

  {
    path: 'home',
    component: HomePageComponent
  },

  {
    path: 'login',
    component: LoginComponent,
    canActivate: [loginGuard]

  },

  {
    path: 'app',
    component: MainPageComponent,
    canActivate: [authGuard]
  },

];
