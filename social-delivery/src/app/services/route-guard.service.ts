import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router, ActivatedRouteSnapshot } from '@angular/router';

@Injectable()
export class RouteGuardService {
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  public canActivate(route: ActivatedRouteSnapshot): boolean {
    const data = route.data as any;
    if (!data.roles) {
      console.log("nem jó1")
      return true;
    }
    if (this.authService.isLoggedIn() && data.roles.includes(this.authService.getRole())) 
    {
      console.log("nem jó2")
      return true;
    }
    this.router.navigate([''], { queryParams: { from: route.url } });
    console.log("nem jó3")
    return false;
  }

  public canActivateChild(route: ActivatedRouteSnapshot): boolean {
    return this.canActivate(route);
  }

}
