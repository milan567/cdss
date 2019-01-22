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
    public doctorName:string,
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
    public examinations: Examination[] = [],
  ) {}
}

export class Link
{
  constructor( public text: string,
               public routerLink: string
  ){}
}

export class Examination {
  constructor( public date: string,
               public disease: Disease,
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
              public symptomType:string,
  ){}
}

export class Ingredient
{
  constructor(public id:string,
              public text:string,
  ){}
}

export class Medication
{
  constructor(public id:string,
              public text:string,
              public ingredients:Ingredient[],
              public medicationType:string,
  ){}
}

export class Disease
{

  constructor( public id:string,
               public disease:string,
               public group:number,
               public symptoms:Symptom[]) {}
}


export class PotentialDisease{

  constructor( public disease:Disease,
               public satisfiedSymptoms:number) {}

}

export class Condition{

  constructor( public conditionSatisfied:boolean,
               public message:string[]) {}


}

