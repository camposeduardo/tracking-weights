import { Component } from '@angular/core';
import { NavbarProgressComponent } from "./navbar-progress/navbar-progress.component";
import { CardExerciseComponent } from "./card-exercise/card-exercise.component";
import { MatDialog } from '@angular/material/dialog';
import { AddExerciseDialogComponent } from '../../dialogs/add-exercise-dialog/add-exercise-dialog.component';

@Component({
  selector: 'progress-app',
  standalone: true,
  imports: [NavbarProgressComponent, CardExerciseComponent],
  templateUrl: './progress.component.html',
  styleUrl: './progress.component.css'
})
export class ProgressComponent {


  constructor(private dialog: MatDialog) { }

  openDialog(): void {
    this.dialog.open(AddExerciseDialogComponent, {
      width: '300px',
      data: { message: 'Hello from Add Exercise Dialog!' },
    });
  }
}
