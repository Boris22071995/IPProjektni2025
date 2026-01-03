import { CvEducation } from "./cv-education";
import { CvExperience } from "./cv-experience";
import { CvInterest } from "./cv-interest";
import { CvSkill } from "./cv-skill";

export interface Cv {
  profileImage?: File | null;
  email: string;
  phone: string;
  address: string;
  city: string;
  postCode: string;
  dateOfBirth: Date;
  studentId: number;

  educations: CvEducation[];
  experiences: CvExperience[];
  skills: CvSkill[];
  interests: CvInterest[];
}
