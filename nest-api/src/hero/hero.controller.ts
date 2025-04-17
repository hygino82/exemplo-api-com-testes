import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';
import { CreateHeroDto } from './dto/create-hero.dto';
import { Hero } from './entities/hero.entity';
import { HeroService } from './hero.service';
import { UpdateHeroDto } from './dto/update-hero.dto';

@Controller('hero')
export class HeroController {
  constructor(private readonly heroService: HeroService) {}

  @Post()
  create(@Body() createHeroDto: CreateHeroDto): Promise<Hero> {
    return this.heroService.create(createHeroDto);
  }

  @Get()
  findAll(): Promise<Hero[]> {
    return this.heroService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: number) {
    return this.heroService.findOne(id);
  }

  @Put(':id')
  update(@Param('id') id: number, @Body() updateHeroDto: UpdateHeroDto) {
    return this.heroService.update(id, updateHeroDto);
  }

  @Delete(':id')
  remove(@Param('id') id: number) {
    return this.heroService.remove(id);
  }
}
