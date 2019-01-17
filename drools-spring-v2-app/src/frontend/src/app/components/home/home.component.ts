import { Component } from '@angular/core';
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  moduleId: module.id,
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']

})
export class HomeComponent
{
  private usr: string;
  private password: string;
  private role: string;

  constructor(private authenticationService: AuthenticationService)
  {
    this.usr = "admin";
    this.password = "admin";
  }

  authenticate()
  {
    this.authenticationService.authenticateUser(this.usr, this.password).subscribe(
      data => {
        localStorage.setItem("loggedUser", JSON.stringify(data));
        this.role = data.role;
      },
      error => this.badInput(),
      () => {
        this.callEmitter();
      }
    );

  }

  callEmitter()
  {
    this.authenticationService.emitRole(this.role);
  }

  badInput()
  {
    document.getElementById("login").innerHTML = "<div class=\"alert alert-danger col-sm-offset-4 \"> Wrong email/password! </div>";
  }

}
