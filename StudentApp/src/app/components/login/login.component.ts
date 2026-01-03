import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms'
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

 loginForm;
 errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(): void {
     if (this.loginForm.invalid) {
      return;
    }

    const request = {
      username: this.loginForm.value.username!,
      password: this.loginForm.value.password!,
      role: 'STUDENT'
    };

    this.authService.login(request).subscribe({
      next: () => {
        alert('You are loged in');
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.errorMessage = 'Wrong username or password';
      }
    });
  }

}
