import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MoviedetailsService {

  constructor(private http: HttpClient) { }

  getMovieDetails(movieId : any) {
    return this.http.get("/api/movies/" + movieId);
  }

  // getMovieDetails(movieId : any) {
  //    return this.http.get("http://localhost:8080/api/movies/" + movieId);
  //  }
}
