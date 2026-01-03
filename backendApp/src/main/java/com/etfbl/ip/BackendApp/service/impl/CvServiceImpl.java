package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.*;
import com.etfbl.ip.BackendApp.model.requests.CvRequest;
import com.etfbl.ip.BackendApp.repository.*;
import com.etfbl.ip.BackendApp.service.CvService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    private final CvRepository cvRepository;
    private final StudentRepository studentRepository;
    private final CvEducationRepository cvEducationRepository;
    private final CvExperienceRepository cvExperienceRepository;
    private final CvSkillRepository cvSkillRepository;
    private final CvInterestRepository cvInterestRepository;

    @Override
    @Transactional
    public void createCv(CvRequest request, MultipartFile image) {
        Student student = studentRepository.findByIndexNumber(request.getStudent().getIndexNumber());
        Cv cv = new Cv();
        cv.setEmail(request.getCv().getEmail());
        cv.setPhone(request.getCv().getPhone());
        cv.setAddress(request.getCv().getAddress());
        cv.setCity(request.getCv().getCity());
        cv.setPostCode(request.getCv().getPostCode());
        cv.setDateOfBirth(request.getCv().getDateOfBirth());
        cv.setStudent(student);

        if (image != null && !image.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path uploadDir = Paths.get("uploads/photos");

                Files.createDirectories(uploadDir);
                Files.copy(image.getInputStream(), uploadDir.resolve(fileName));

                cv.setProfileImagePath("photos/" + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed");
            }
        } else {
            cv.setProfileImagePath("");
        }

        cvRepository.save(cv);

        request.getEducations().forEach(e -> {
            CvEducation edu = new CvEducation();
            edu.setNameOfInstitution(e.getNameOfInstitution());
            edu.setTitleName(e.getTitleName());
            edu.setStartDate(e.getStartDate());
            edu.setEndDate(e.getEndDate());
            edu.setCv(cv);
            cvEducationRepository.save(edu);
        });

        request.getExperiences().forEach(e -> {
            CvExperience exp = new CvExperience();
            exp.setCompany(e.getCompany());
            exp.setPosition(e.getPosition());
            exp.setDescription(
                    e.getDescription() == null ? "" : e.getDescription()
            );
            exp.setStartDate(e.getStartDate());
            exp.setEndDate(e.getEndDate());
            exp.setCv(cv);
            cvExperienceRepository.save(exp);
        });

        request.getSkills().forEach(s -> {
            CvSkill skill = new CvSkill();
            skill.setSkillName(s.getSkillName());
            skill.setLevel(s.getLevel());
            skill.setCv(cv);
            cvSkillRepository.save(skill);
        });

        request.getInterests().forEach(i -> {
            CvInterest interest = new CvInterest();
            interest.setInterestName(i.getInterestName());
            interest.setCv(cv);
            cvInterestRepository.save(interest);
        });
    }
}
