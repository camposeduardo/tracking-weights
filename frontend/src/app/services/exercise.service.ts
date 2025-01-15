import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';
import { environment } from '../../environments/environment.development';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { map } from 'rxjs/internal/operators/map';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private cardExerciseDataSubject = new BehaviorSubject<Exercise[]>([]);
  cardData = this.cardExerciseDataSubject.asObservable();

  constructor(private http: HttpClient) { }

  addExercise(exercise: Exercise) {
    return this.http.post<any>(`${environment.apiUrl}/exercise/add`, exercise, { withCredentials: true });
  }

  getAllExercises() {
    return this.http.get<Exercise[]>(`${environment.apiUrl}/exercise/all`, { withCredentials: true }).pipe(
      map(response => {
        this.cardExerciseDataSubject?.next(response);
        return response;
      }), (err) => {
        return err;
      });;
  }

  getAllMuscleGroups() {
    return this.http.get<any>(`${environment.apiUrl}/exercise/muscleGroups`, { withCredentials: true });
  }

  getExercisesRelated(exerciseName: string) {
    const options =
    {
      params: new HttpParams().set('search', exerciseName),
      withCredentials: true,
    };

    return this.http.get<any>(`${environment.apiUrl}/exercise`,
      options
    ).pipe(
      map(response => {
        this.cardExerciseDataSubject?.next(response);
        return response;
      }), (err) => {
        return err;
      });
  }

  getExercisesByMuscleGroup(muscleGroup: string) {
    return this.http.get<any>(`${environment.apiUrl}/exercise/${muscleGroup}`, { withCredentials: true }).pipe(
      map(response => {
        this.cardExerciseDataSubject?.next(response);
        return response;
      }), (err) => {
        return err;
      });
  }

  deleteExercise(exerciseId: number) {
    return this.http.delete<any>(`${environment.apiUrl}/exercise/delete/${exerciseId}`, { withCredentials: true });
  }

  updateExercise(exercise: Exercise) {
    return this.http.post<any>(`${environment.apiUrl}/exercise/update`, exercise, { withCredentials: true });
  }

}
