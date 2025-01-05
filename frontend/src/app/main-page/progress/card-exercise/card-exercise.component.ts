import { Component } from '@angular/core';
import { Exercise } from '../../../models/Exercise';
import { ExerciseService } from '../../../services/exercise.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'card-exercise',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-exercise.component.html',
  styleUrl: './card-exercise.component.css'
})
export class CardExerciseComponent {

    exercises_data!: Observable<Exercise[]>;

    constructor(private exerciseService: ExerciseService) {}

    ngOnInit() {
      this.exercises_data = this.exerciseService.cardData;
    }
}
