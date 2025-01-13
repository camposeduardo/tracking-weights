import { Component, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AddExerciseDialogComponent } from '../add-exercise-dialog/add-exercise-dialog.component';
import { CommonModule } from '@angular/common';
import { Exercise } from '../../models/Exercise';

@Component({
  selector: 'app-update-exercise-dialog',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './update-exercise-dialog.component.html',
  styleUrl: './update-exercise-dialog.component.css'
})
export class UpdateExerciseDialogComponent {

  editExerciseForm!: FormGroup;
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

    constructor(@Inject(MAT_DIALOG_DATA) public data: any,
      private dialogRef: MatDialogRef<AddExerciseDialogComponent>,
      private fb: FormBuilder) {
    }

    ngOnInit() {
      this.editExerciseForm = this.fb.group({
        exerciseName: [this.data.exerciseName || '', Validators.required],
        muscleGroup: [this.data.muscleGroup || '', Validators.required],
        weight: [this.data.weight || null, [Validators.required, Validators.min(1)]],
        unit: [this.data.unit || 'kg', Validators.required],
        reps: [this.data.reps || null, [Validators.required, Validators.min(1)]],
        date: [this.data.date || '', Validators.required],
      });
    }

    onCancel(): void {
      this.dialogRef.close();
    }

    onSubmit(): void {
      if (this.editExerciseForm.valid) {
        const exerciseEdited = {
          id: this.data.id,
          ...this.editExerciseForm.value
        }
        this.dialogRef.close(exerciseEdited);
      }
    }

}
