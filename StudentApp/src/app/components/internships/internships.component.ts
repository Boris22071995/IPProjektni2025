import { Component, OnInit } from '@angular/core';
import { Internship } from '../../models/internship';
import { CommonModule } from '@angular/common';

import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { InternshipService } from '../../services/internship/internship.service';

@Component({
  selector: 'app-internships',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatTableModule,
    MatChipsModule,
    MatIconModule
  ],
  templateUrl: './internships.component.html',
  styleUrl: './internships.component.css'
})
export class InternshipsComponent implements OnInit{

   internships: Internship[] = [];
  displayedColumns: string[] = [
    'title',
    'company',
    'technologies',
    'dates',
    'actions'
  ];

  constructor(private internshipService: InternshipService) {}

  ngOnInit(): void {
    this.loadInternships();
  }

  loadInternships() {
    this.internshipService.getAll().subscribe({
      next: data => this.internships = data,
      error: err => console.error('Error loading internships', err)
    });
  }

  apply(internship: Internship) {
     this.internshipService.apply(internship.id).subscribe({
      next: () => {
        alert('Successfully applied!');
      },
      error: err => {
        alert(err.error?.message || 'Application failed');
      }
    });
  }

  viewDetails(internship: Internship) {
    console.log('View details:', internship);
    // FAZA 3
  }

}
