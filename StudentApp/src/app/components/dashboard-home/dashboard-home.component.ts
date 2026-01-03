import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-home',
  imports: [],
  templateUrl: './dashboard-home.component.html',
  styleUrl: './dashboard-home.component.css'
})
export class DashboardHomeComponent implements OnInit{
   firstName = '';
  lastName = '';
  indexNumber = '';
  yearOfStudy: number | null = null;

  ngOnInit(): void {
    const user = JSON.parse(localStorage.getItem('loggedUser')!);

    if (user) {
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.indexNumber = user.indexNumber;
      this.yearOfStudy = user.yearOfStudy;
    }
  }
}
