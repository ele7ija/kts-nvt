import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  constructor() { }

  projectUrl : string = environment.projectUrl;
  bojanUrl: string = environment.githubUrlBojan;
  milanUrl: string = environment.githubUrlMilan;
  ivanaUrl: string = environment.githubUrlIvana;

  ngOnInit(): void {
  }

}
