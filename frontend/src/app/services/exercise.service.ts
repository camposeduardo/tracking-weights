import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  constructor(private http: HttpClient) { }

  addExercise(exercise: Exercise) {
    return this.http.post<any>(`${environment.apiUrl}/exercise/add`, exercise, { withCredentials: true });
  }

  getAllMuscleGroups() {
    return this.http.get<any>(`${environment.apiUrl}/exercise/muscleGroups`, { withCredentials: true });
  }
}
