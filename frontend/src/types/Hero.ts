export interface Hero {
  id: number;
  name: string;
  codename: string;
  age: number;
  country: string;
  team: string;
}

export interface InsertHero {
  name: string;
  codename: string;
  age: number;
  country: string;
  team: string;
}
