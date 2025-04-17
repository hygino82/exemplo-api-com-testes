import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Hero } from './entities/hero.entity';
import { HeroController } from './hero.controller';
import { HeroService } from './hero.service';

@Module({
  imports: [TypeOrmModule.forFeature([Hero])],
  controllers: [HeroController],
  providers: [HeroService],
})
export class HeroModule {}
