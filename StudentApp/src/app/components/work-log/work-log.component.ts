import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-work-log',
  standalone: true,
  imports: [ CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDividerModule],
  templateUrl: './work-log.component.html',
  styleUrl: './work-log.component.css'
})
export class WorkLogComponent {

   internshipTitle = 'Frontend Developer Internship';
  companyName = 'Tech Solutions d.o.o.';

  workLogForm: FormGroup;

  // MOCK lista unosa
  workLogs: any[] = [
    {
      weekNumber: 1,
      activities: 'Introduction to project, environment setup'
    },
    {
      weekNumber: 2,
      activities: 'Implemented login page using Angular Material'
    }
  ];

  constructor(private fb: FormBuilder) {
    this.workLogForm = this.fb.group({
      weekNumber: ['', Validators.required],
      activities: ['', Validators.required]
    });
  }

  saveWorkLog() {
    if (this.workLogForm.invalid) return;

    this.workLogs.push(this.workLogForm.value);
    this.workLogForm.reset();
  }
}
