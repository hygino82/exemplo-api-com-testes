import { IsNotEmpty, IsString, MinLength, IsInt, Min } from 'class-validator';

export class CreateHeroDto {
  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  name: string;

  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  codename: string;

  @IsNotEmpty()
  @IsInt()
  @Min(1)
  age: number;

  @IsNotEmpty()
  @IsString()
  @MinLength(3)
  country: string;
}
