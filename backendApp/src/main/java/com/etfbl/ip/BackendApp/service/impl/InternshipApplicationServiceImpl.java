package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Internship;
import com.etfbl.ip.BackendApp.model.InternshipApplication;
import com.etfbl.ip.BackendApp.model.Student;
import com.etfbl.ip.BackendApp.model.requests.InternshipApplicationRequest;
import com.etfbl.ip.BackendApp.model.requests.InternshipRequest;
import com.etfbl.ip.BackendApp.model.respons.InternshipResponse;
import com.etfbl.ip.BackendApp.repository.InternshipApplicationRepository;
import com.etfbl.ip.BackendApp.repository.InternshipRepository;
import com.etfbl.ip.BackendApp.repository.StudentRepository;
import com.etfbl.ip.BackendApp.service.InternshipApplicationService;
import com.etfbl.ip.BackendApp.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InternshipApplicationServiceImpl implements InternshipApplicationService {

    private final StudentRepository studentRepository;
    private final InternshipRepository internshipRepository;
    private final InternshipApplicationRepository internshipApplicationRepository;

    @Override
    public void apply(InternshipApplicationRequest request) {
        Student student = studentRepository.findByIndexNumber(request.getIndexNumber());
        Optional<Internship> internship = internshipRepository.findById(request.getInternshipId());

        if(internshipApplicationRepository.existsByStudentAndInternship(student, internship)){
            throw new RuntimeException("Already applied");
        }
        Internship newInt = internship.orElseThrow();
        InternshipApplication app = new InternshipApplication();
        app.setStudent(student);
        app.setInternship(newInt);
        app.setStatus("PENDING");
        app.setAppliedAt(LocalDateTime.now());

        internshipApplicationRepository.save(app);
    }
}
