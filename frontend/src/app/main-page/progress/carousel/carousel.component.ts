import { Component } from '@angular/core';
import { CardExerciseComponent } from "./card-exercise/card-exercise.component";

@Component({
  selector: 'carousel',
  standalone: true,
  imports: [CardExerciseComponent],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

}
