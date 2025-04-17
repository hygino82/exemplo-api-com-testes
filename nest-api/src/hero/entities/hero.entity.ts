import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity()
export class Hero {
  @PrimaryGeneratedColumn()
  id?: number;

  @Column({ length: 100, nullable: false })
  name: string;

  @Column({ length: 100, nullable: false })
  codename: string;

  @Column({ nullable: false })
  age: number;

  @Column({ length: 100, nullable: false })
  country: string;
}
