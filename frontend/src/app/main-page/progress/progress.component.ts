import { Component } from '@angular/core';
import { NavbarProgressComponent } from "./navbar-progress/navbar-progress.component";
import { CardExerciseComponent } from "./card-exercise/card-exercise.component";

@Component({
  selector: 'progress-app',
  standalone: true,
  imports: [NavbarProgressComponent, CardExerciseComponent],
  templateUrl: './progress.component.html',
  styleUrl: './progress.component.css'
})
export class ProgressComponent {

}
