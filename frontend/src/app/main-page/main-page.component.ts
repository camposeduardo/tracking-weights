import { Component } from '@angular/core';
import { ProgressComponent } from './progress/progress.component';

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [ProgressComponent],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {

}
