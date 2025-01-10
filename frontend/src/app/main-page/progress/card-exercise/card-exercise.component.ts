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

    onDeleteButton(exercise: Exercise) {
      if (confirm('Are you sure you want to delete this card?')) {
        this.exerciseService.deleteExercise(exercise.id).subscribe({
          next: (exercises) => {
            this.exercises_data = this.exercises_data.filter(card => card.id !== exercise.id);
          }
        });
      }
    }
}
