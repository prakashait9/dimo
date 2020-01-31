import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../../model/movie.model';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  getMoviesByPreferredGenres() {
    var token = "Bearer " +  localStorage.getItem("id_token");
    var httpHeaders = new HttpHeaders();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const options = {
      headers: new HttpHeaders().append('Auth', 'value')
    }
    httpHeaders.set('Authorization', "test");


    const headerDict = {
      'Content-Type': 'application/json',
      'Authorization': token,
      'test': 'test',
      'Access-Control-Allow-Headers': 'Content-Type',
    }
    
    const requestOptions = {                                                                                                                                                                                 
      headers: new Headers(headerDict), 
    };
    return this.http.get(`http://localhost:8080/api/getbygenre?limit=4`,{headers: new HttpHeaders(headerDict)});
  }
}

  // return of([
    //   {
    //     genre: 1,
    //     genrename: "Trending",
    //     movies: [
    //       {
    //         title: 'Final Fantasy Advent Children',
    //         movieid: 1,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Final Fantasy Advent Children',
    //         overview: 'New York, the largest city in the U.S., is an architectural marvel with plenty of historic monuments, magnificent buildings and countless dazzling skyscrapers.',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       },
    //       {
    //         title: 'Logan',
    //         movieid: 2,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Logan',
    //         overview: '',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       },
    //       {
    //         title: 'Logan',
    //         movieid: 3,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Logan',
    //         overview: '',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       },
    //       {
    //         title: 'Logan',
    //         movieid: 4,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Logan',
    //         overview: '',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       },
    //       {
    //         title: 'Logan',
    //         movieid: 5,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Logan',
    //         overview: '',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       }
    //     ]
    //   },
    //   {
    //     genre: 2,
    //     genrename: "Romance",
    //     movies: [
    //       {
    //         title: 'Time Travelers Wife',
    //         movieid: 6,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Time Travelers Wife',
    //         overview: '',
    //         popularity: 9,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       },
    //       {
    //         title: 'Deadpool',
    //         movieid: 7,
    //         budget: 340005,
    //         homepage: 'http',
    //         origLang: 'English',
    //         origTitle: 'Deadpool',
    //         overview: '',
    //         popularity: 10,
    //         releaseDate: new Date(),
    //         revenue: 12345,
    //         runtime: 120,
    //         status: 'good',
    //         tagline: 'not again',
    //         voteAverage: 'cool',
    //         voteCount: 345,
    //         genre: [{ id: 1, name: 'fantasy' }],
    //         keyword: [{}]
    //       }
    //     ]
    //   }
    // ]);
