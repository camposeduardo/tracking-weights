import { Component } from '@angular/core';
import { Exercise } from '../../../models/Exercise';
import { ExerciseService } from '../../../services/exercise.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'card-exercise',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-exercise.component.html',
  styleUrl: './card-exercise.component.css'
})
export class CardExerciseComponent {

    exercises_data: Exercise[] = [];

    constructor(private exerciseService: ExerciseService) {}

    ngOnInit() {
      this.exerciseService.cardData.subscribe({
        next: (exercises) => {
          if (exercises) {
            this.exercises_data = exercises;
          }
        }
      });
    }
}
