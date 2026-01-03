import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

/* Angular Material */
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatOption } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select'
import {
  FormArray,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cv-create',
  standalone: true,
  imports: [ CommonModule,
    ReactiveFormsModule,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatDatepickerModule,
    MatNativeDateModule, MatOption, MatSelectModule],
  templateUrl: './cv-create.component.html',
  styleUrl: './cv-create.component.css'
})
export class CvCreateComponent {
   cvForm: FormGroup;
  imagePreview: string | null = null;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.cvForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      postCode: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      profileImage: [null],

      educations: this.fb.array([this.createEducation()]),
      experiences: this.fb.array([this.createExperience()]),
      skills: this.fb.array([this.createSkill()]),
      interests: this.fb.array([this.createInterest()])
    });
  }

  get educations() {
    return this.cvForm.get('educations') as FormArray;
  }

  get experiences() {
    return this.cvForm.get('experiences') as FormArray;
  }

  get skills() {
    return this.cvForm.get('skills') as FormArray;
  }

  get interests() {
    return this.cvForm.get('interests') as FormArray;
  }

  createEducation(): FormGroup {
    return this.fb.group({
      nameOfInstitution: ['', Validators.required],
      titleName: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  createExperience(): FormGroup {
    return this.fb.group({
      company: [''],
      position: [''],
      description: [''],
      startDate: [''],
      endDate: ['']
    });
  }

  createSkill(): FormGroup {
    return this.fb.group({
      skillName: [''],
      level: ['']
    });
  }

  createInterest(): FormGroup {
    return this.fb.group({
      interestName: ['']
    });
  }

  addEducation() {
    this.educations.push(this.createEducation());
  }

  removeEducation(i: number) {
    this.educations.removeAt(i);
  }

  addExperience() {
    this.experiences.push(this.createExperience());
  }

  removeExperience(i: number) {
    this.experiences.removeAt(i);
  }

  addSkill() {
    this.skills.push(this.createSkill());
  }

  removeSkill(i: number) {
    this.skills.removeAt(i);
  }

  addInterest() {
    this.interests.push(this.createInterest());
  }

  removeInterest(i: number) {
    this.interests.removeAt(i);
  }

  onImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.cvForm.patchValue({ profileImage: file });

      const reader = new FileReader();
      reader.onload = () => this.imagePreview = reader.result as string;
      reader.readAsDataURL(file);
    }
  }

  submit() {
    if (this.cvForm.invalid) return;
    const loggedUser = JSON.parse(localStorage.getItem('loggedUser')!);

    const cvPayload = {
      student: {
        username: loggedUser.firstName,
        firstName: loggedUser.firstName,
        lastName: loggedUser.lastName,
        yearOfStudy: loggedUser.yearOfStudy,
        indexNumber: loggedUser.indexNumber
      },
      cv: {
        email: this.cvForm.value.email,
        phone: this.cvForm.value.phone,
        address: this.cvForm.value.address,
        city: this.cvForm.value.city,
        postCode: this.cvForm.value.postCode,
        dateOfBirth: this.cvForm.value.dateOfBirth
      },
      educations: this.cvForm.value.educations,
      experiences: this.cvForm.value.experiences.map((e: any) => ({
      company: e.company || ' ',
      position: e.position || ' ',
      description: e.description || ' ',
      startDate: e.startDate || null,
      endDate: e.endDate || null
    })),

    skills: this.cvForm.value.skills.map((s: any) => ({
      skillName: s.skillName || ' ',
      level: s.level || ' '
    })),

    interests: this.cvForm.value.interests.map((i: any) => ({
      interestName: i.interestName || ' '
    }))
    }

    const formData = new FormData();
    formData.append('cv',new Blob([JSON.stringify(cvPayload)], {type: 'application/json'}));

    if (this.cvForm.value.profileImage) {
    formData.append('image', this.cvForm.value.profileImage);
  }

  this.http.post('http://localhost:8080/api/cv', formData)
    .subscribe(() => {
      console.log('CV saved');
      this.cvForm.reset();
      this.imagePreview = null;
    });

    
  }
}
