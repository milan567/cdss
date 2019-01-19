import construct = Reflect.construct;

export class Log
{
  constructor( public id:number,
               public type:string,
               public description:string,
               public date:Date,
  ) {}
}

export class User
{
  constructor(
    public username:string,
    public role:string,
  ) {}
}

export class Doctor
{
  constructor(
    public id:string,
    public name:string,
    public surname:string,
    public username:string,
    public specialist:string,
  ) {}
}

export class Patient
{
  constructor(
    public id:string = "",
    public patientName:string = "",
    public patientSurame:string = "",
  ) {}
}

export class Link
{
  constructor( public text: string,
               public routerLink: string
  ){}
}



export class Address
{
  constructor( public id:number,
               public city:string,
               public number:string,
               public street:string,
               public postalCode:string,
               public country:string,
  ) {}
}



export class Symptom
{
  constructor(public id:string,
              public text:string,
  ){}
}

export class Disease
{

  constructor( public id:string,
               public disease:string,
               public group:number,
               public symptoms:Symptom[]) {}
}




