import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras, ActivatedRoute} from '@angular/router';
import { MoviedetailsService } from './moviedetails.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-moviedetails',
  templateUrl: './moviedetails.component.html',
  styleUrls: ['./moviedetails.component.css']
})
export class MoviedetailsComponent implements OnInit {

 movie: any;
 userName : any;
 movieId : any;


  constructor(private router: Router, private movieService: MoviedetailsService, private activeRoute: ActivatedRoute, private toaster: ToastrService) {
    this.movieId = this.activeRoute.snapshot.queryParamMap.get('movieId')
  }

  ngOnInit() {
    this.userName = '';
    this.getMovieDetails(this.movieId);
  }

 getMovieDetails(movieId: any) {

  this.movieService.getMovieDetails(movieId)
    .subscribe(
      data => {
        if(data["data"]) {
          this.movie = data["data"];
        } else {
          this.naviagateToDashboard();
          this.toaster.info('', 'Please try again!!', { timeOut: 3000 });
        }
      },
      error => {
        console.log("error");
        this.toaster.info('', 'Please try again!!', { timeOut: 3000 });
        this.naviagateToDashboard();
      });
    }

    naviagateToDashboard() {
      this.router.navigate(['/dashboard']);
    }

}
