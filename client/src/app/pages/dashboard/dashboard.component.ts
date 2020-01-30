import { Component, OnInit } from '@angular/core';
import { DashboardService } from './dashboard.service';

import { Movie } from '../../model/movie.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  body;
  favouriteGenreMovies: Object[] = [];
  constructor(
    private dashboardService: DashboardService,
  ) { }

  ngOnInit(): void {
    this.body = document.getElementsByTagName('body')[0];
    this.body.classList.remove('bg-default');
    this.body.classList.add('grey-background');
    this.getMoviesByPreferredGenres();
  }

  getMoviesByPreferredGenres() {
    this.dashboardService.getMoviesByPreferredGenres().
      subscribe(
        genres => {
            genres.forEach(genre => {
                while (genre.movies.length < 4){
                    genre.movies.push({});
                }
            });
            this.favouriteGenreMovies = genres;
        },
        error => {
          console.log('error', error);
        }
      )
  }

}
