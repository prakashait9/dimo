import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-moviedetails',
  templateUrl: './moviedetails.component.html',
  styleUrls: ['./moviedetails.component.css']
})
export class MoviedetailsComponent implements OnInit {

 movie: any;


  constructor() { }

  ngOnInit() {

    this.movie = {
    "success":true,
    "timestamp":"30-01-2020 12:21:04",
    "error":null,
    "data":{
       "_id":157336,
       "origTitle":"Interstellar",
       "releaseDate":"2014-11-04T18:30:00.000+0000",
       "origLang": [
          {
            "iso_639_1": "en",
            "name": "English"
          },
            {
              "iso_639_1": "es",
              "name": "Espa\u00f1ol"
            }
          ],
       "runtime":169,
       "genre":[
          {
             "id":0,
             "name":"Adventure"
          },
          {
             "id":0,
             "name":"Drama"
          },
          {
             "id":0,
             "name":"Science Fiction"
          }
       ],
       "overview":"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.",
       "poster":"https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg(34 kB)"
    },
    "error_message":null
    };

this.movie = this.movie.data;

  }

}
