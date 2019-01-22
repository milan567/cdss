import { Component, NgModule, OnInit } from '@angular/core';
import {LoggedUtils} from "../../utils/logged.utils";
import { AuthenticationService } from "../../services/authentication.service";
import { Link } from "../../models";
import { Router }    from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'navbar',
  templateUrl: 'navbar.component.html'
})
export class NavbarComponent implements OnInit
{

  links: Link[];
  currentRole:string;

  constructor(private authenticationService: AuthenticationService, private _router: Router)
  {
    this.logout();
  }

  ngOnInit() {
    this.authenticationService.getRoleEmitter().subscribe((value:string) => this.changeRole(value));
  }

  private changeRole(role: string)
  {
    this.currentRole = role;
    this.links = [];

    if (this.currentRole == "ADMIN")
      this.presetAdmin();
    else if (this.currentRole == "DOCTOR")
      this.presetUser();
  }


  addLink(link: Link)
  {
    this.links.push(link);
  }

  presetAdmin()
  {
    this.addLink({text: "Dodaj nova pravila", routerLink: "/dodajPravila"});
    this.addLink({text: "Doktori", routerLink: "/sviDoktori"});
  }

  presetUser()
  {
    this.addLink( {text: "Pacijenti", routerLink: "/sviPacijenti"});
    this.addLink({text: "Bolesti", routerLink: "/sveBolesti"});
    this.addLink({text: "Izvjestaj", routerLink: "/izvjestaj"});
  }


  logout()
  {
    this.links = [];
    this.addLink({text: "Login", routerLink: "/"});
    LoggedUtils.clearLocalStorage();
  }
}
