import {Hero} from './Hero';

export interface PageRequest {
  content: Hero[];
  totalElements: number;
  totalPages: number;
  last: boolean;
  size: number;
  number: number;
  sort: {
    sorted: boolean;
    unsorted: boolean;
    empty: boolean;
  },
  numberOfElements: 10,
  first: true,
  empty: false
}
