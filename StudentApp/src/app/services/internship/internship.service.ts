import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Internship } from '../../models/internship';

@Injectable({
  providedIn: 'root'
})
export class InternshipService {

   private apiUrl = 'http://localhost:8080/api/internships';
   private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Internship[]> {
    return this.http.get<Internship[]>(this.apiUrl);
  }

   apply(internshipId: number): Observable<void> {
    const loggedUser = JSON.parse(localStorage.getItem('loggedUser')!);

    const payload = {
      internshipId: internshipId,
      indexNumber: loggedUser.indexNumber
    };

    return this.http.post<void>(
      `${this.baseUrl}/internship-applications`,
      payload
    );
  }
}
