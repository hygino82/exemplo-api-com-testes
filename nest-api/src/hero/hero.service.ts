import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateHeroDto } from './dto/create-hero.dto';
import { Hero } from './entities/hero.entity';
import { UpdateHeroDto } from './dto/update-hero.dto';

@Injectable()
export class HeroService {
  constructor(
    @InjectRepository(Hero)
    private heroRepository: Repository<Hero>,
  ) {}

  create(createHeroDto: CreateHeroDto): Promise<Hero> {
    const hero: Hero = { ...createHeroDto };
    return this.heroRepository.save(hero);
  }

  findAll(): Promise<Hero[]> {
    return this.heroRepository.find();
  }

  async findOne(id: number): Promise<Hero | null> {
    return this.heroRepository.findOneBy({ id });
  }

  async update(id: number, updateHeroDto: UpdateHeroDto): Promise<Hero | null> {
    const hero = await this.heroRepository.findOneBy({ id });

    if (!hero) {
      return null;
    }

    const updated = { ...hero, ...updateHeroDto };
    return this.heroRepository.save(updated);
  }

  remove(id: number): void {
    this.heroRepository.delete({ id });
  }
}
