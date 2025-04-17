import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { Hero } from './hero/entities/hero.entity';
import { HeroModule } from './hero/hero.module';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '89631139',
      database: 'testdb',
      entities: [Hero],
      synchronize: true,
    }),
    HeroModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
