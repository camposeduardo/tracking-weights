import { Component } from '@angular/core';
import { NavbarProgressComponent } from "./navbar-progress/navbar-progress.component";
import { CardExerciseComponent } from "./card-exercise/card-exercise.component";
import { MatDialog } from '@angular/material/dialog';
import { AddExerciseDialogComponent } from '../../dialogs/add-exercise-dialog/add-exercise-dialog.component';
import { Exercise } from '../../models/Exercise';
import { ExerciseService } from '../../services/exercise.service';

@Component({
  selector: 'progress-app',
  standalone: true,
  imports: [NavbarProgressComponent, CardExerciseComponent],
  templateUrl: './progress.component.html',
  styleUrl: './progress.component.css'
})
export class ProgressComponent {

  constructor(private dialog: MatDialog, private exerciseService: ExerciseService) { }

  onSearchExerciseButton(value: string) {
    this.exerciseService.getExercisesRelated(value).subscribe({
      next: (exercise) => {
        console.log(exercise)
      }
    }
    );
  }

  openDialog(): void {
    const dialogRef = this.dialog.open<AddExerciseDialogComponent, any, Exercise>(AddExerciseDialogComponent, {
      width: '25vw',
    });
    dialogRef.afterClosed().subscribe((exercise: Exercise | undefined) => {
      if (exercise) {
        this.exerciseService.addExercise(exercise).subscribe();
      }
    });
  }
}
