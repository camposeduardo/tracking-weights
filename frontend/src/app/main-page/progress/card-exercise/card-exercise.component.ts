import { Component } from '@angular/core';
import { Exercise } from '../../../models/Exercise';
import { ExerciseService } from '../../../services/exercise.service';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UpdateExerciseDialogComponent } from '../../../dialogs/update-exercise-dialog/update-exercise-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'card-exercise',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-exercise.component.html',
  styleUrl: './card-exercise.component.css'
})
export class CardExerciseComponent {

  exercises_data: Exercise[] = [];

  constructor(private dialog: MatDialog, private exerciseService: ExerciseService) { }

  ngOnInit() {
    this.exerciseService.cardData.subscribe({
      next: (exercises) => {
        if (exercises) {
          this.exercises_data = exercises;
        }
      }
    });
  }

  openDialog(exercise: Exercise): void {
    const dialogRef = this.dialog.open(UpdateExerciseDialogComponent, {
      data: exercise,
    });
    dialogRef.afterClosed().subscribe((exercise: Exercise | undefined) => {
      if (exercise) {
        this.exerciseService.updateExercise(exercise).subscribe({
          next: (exercisee) => {
            this.updateCardOnView(exercise);
          }
        }
        );
      }
    });
  }

  onEditButton(exercise: Exercise) {
    this.openDialog(exercise);
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

  updateCardOnView(exercise: Exercise) {
    const index = this.exercises_data.findIndex(card => card.id === exercise.id);
    if (index !== -1) {
      this.exercises_data[index] = exercise;
    }
  }

}
