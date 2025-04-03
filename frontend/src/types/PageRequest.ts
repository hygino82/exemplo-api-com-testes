import {Hero} from './Hero';

export interface PageRequest {
  content: Hero[];
  totalElements: number;
  totalPages: number;
}
