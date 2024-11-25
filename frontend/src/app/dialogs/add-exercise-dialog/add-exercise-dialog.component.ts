import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-exercise-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './add-exercise-dialog.component.html',
  styleUrls: ['./add-exercise-dialog.component.css'],
})
export class AddExerciseDialogComponent {
  exerciseForm: FormGroup;
  muscleGroups = [
    'Chest',
    'Back',
    'Shoulders',
    'Biceps',
    'Triceps',
    'Forearms',
    'Abs',
    'Legs',
    'Glutes',
  ];

  constructor(private dialogRef: MatDialogRef<AddExerciseDialogComponent>,
    private fb: FormBuilder) {
    this.exerciseForm = this.fb.group({
      exerciseName: ['', Validators.required],
      date: ['', Validators.required],
      weight: [null, [Validators.required, Validators.min(1)]],
      reps: [null, [Validators.required, Validators.min(1)]],
      muscleGroup: ['', Validators.required],
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.exerciseForm.valid) {
      this.dialogRef.close(this.exerciseForm.value);
    }
  }
}
