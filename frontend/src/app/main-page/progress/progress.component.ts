import { Component } from '@angular/core';
import { CarouselComponent } from "./carousel/carousel.component";
import { NavbarProgressComponent } from "../navbar-progress/navbar-progress.component";

@Component({
  selector: 'progress-app',
  standalone: true,
  imports: [CarouselComponent, NavbarProgressComponent],
  templateUrl: './progress.component.html',
  styleUrl: './progress.component.css'
})
export class ProgressComponent {

}
