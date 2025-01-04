import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ExerciseService } from '../../../services/exercise.service';

@Component({
  selector: 'navbar-progress',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar-progress.component.html',
  styleUrl: './navbar-progress.component.css'
})
export class NavbarProgressComponent {

  muscleGroups: string[] | undefined;

  constructor(private exerciseService: ExerciseService) {}

  ngOnInit() {
    this.exerciseService.getAllMuscleGroups().subscribe({
      next: (muscleGroup) => {
        if (muscleGroup) {
          this.muscleGroups = muscleGroup;
        }
      }
    });
  }

}
