import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { StudentModel } from '../../models/student.model';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../../models/login-request';
import { Observable, tap } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly STORAGE_KEY = 'loggedUser';
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) { }

  login(request: LoginRequest): Observable<any> {
     return this.http.post(`${this.apiUrl}/login`, request).pipe(
      tap((student) => {
        // stored loged in user
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(student));
      })
    );
  }

  logout() : void {
    localStorage.removeItem(this.STORAGE_KEY);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem(this.STORAGE_KEY) !== null;
  }

  getLoggedUser(): any {
    const user = localStorage.getItem(this.STORAGE_KEY);
    return user ? JSON.parse(user) : null;
  }
}
