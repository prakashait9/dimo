import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Movie } from '../../model/movie.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  getMoviesByPreferredGenres() {
    // return this.http.get(`http://localhost:8080/get`);
    return of([
      {
        genre: 1,
        genrename: "Trending",
        movies: [
          {
            title: 'Final Fantasy Advent Children',
            movieid: 1,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Final Fantasy Advent Children',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          },
          {
            title: 'Logan',
            movieid: 2,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Logan',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          },
          {
            title: 'Logan',
            movieid: 3,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Logan',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          },
          {
            title: 'Logan',
            movieid: 4,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Logan',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          },
          {
            title: 'Logan',
            movieid: 5,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Logan',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          }
        ]
      },
      {
        genre: 2,
        genrename: "Romance",
        movies: [
          {
            title: 'Time Travelers Wife',
            movieid: 6,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Time Travelers Wife',
            overview: '',
            popularity: 9,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          },
          {
            title: 'Deadpool',
            movieid: 7,
            budget: 340005,
            homepage: 'http',
            origLang: 'English',
            origTitle: 'Deadpool',
            overview: '',
            popularity: 10,
            releaseDate: new Date(),
            revenue: 12345,
            runtime: 120,
            status: 'good',
            tagline: 'not again',
            voteAverage: 'cool',
            voteCount: 345,
            genre: [{ id: 1, name: 'fantasy' }],
            keyword: [{}]
          }
        ]
      }
    ]);
  }
}
