import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import {FormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './auth/token.interceptor';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {enableProdMode} from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AppComponent }  from './app.component';
import {HomeComponent} from "./components/home/home.component";
import { AuthenticationService } from "./services/authentication.service";

import { routing } from "./app.routes";


enableProdMode();


@NgModule({
  imports:      [
    BrowserModule,
    routing,
    HttpModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  declarations: [
    AppComponent,
    NavbarComponent,

    HomeComponent,
  ],
  providers: [
    /*{

      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },*/
    AuthenticationService,
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
