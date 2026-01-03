package com.etfbl.ip.BackendApp.service.impl;

import com.etfbl.ip.BackendApp.model.Company;
import com.etfbl.ip.BackendApp.model.Internship;
import com.etfbl.ip.BackendApp.model.InternshipTechnology;
import com.etfbl.ip.BackendApp.model.requests.InternshipRequest;
import com.etfbl.ip.BackendApp.model.respons.InternshipResponse;
import com.etfbl.ip.BackendApp.repository.CompanyRepository;
import com.etfbl.ip.BackendApp.repository.InternshipRepository;
import com.etfbl.ip.BackendApp.repository.InternshipTechnologyRepository;
import com.etfbl.ip.BackendApp.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;
    private final InternshipTechnologyRepository internshipTechnologyRepository;

    @Override
    public Internship save(InternshipRequest internshipRequest) {
        Company company = companyRepository.findByName(internshipRequest.getCompanyName());
        Internship internship = new Internship();
        internship.setTitle(internshipRequest.getTitle());
        internship.setDescription(internshipRequest.getDescription());
        internship.setRequirements(internshipRequest.getRequirements());
        internship.setStartDate(internshipRequest.getStartDate());
        internship.setEndDate(internshipRequest.getEndDate());
        internship.setRestrictions(internshipRequest.getRestrictions());
        internship.setCompany(company);
        internshipRepository.save(internship);

        for(int i = 0; i < internshipRequest.getTechnologies().size(); i++){
            InternshipTechnology it = new InternshipTechnology();
            it.setInternship(internship);
            it.setTechnologyName(internshipRequest.getTechnologies().get(i));
            internshipTechnologyRepository.save(it);
        }

        return internship;
    }

    @Override
    public List<Internship> getAll() {
        return internshipRepository.findAll();
    }

    @Override
    public List<InternshipResponse> getAllAvailableInternships() {
        return internshipRepository.findAllApproved()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private InternshipResponse mapToResponse(Internship internship) {
        InternshipResponse dto = new InternshipResponse();

        dto.setId(internship.getId());
        dto.setTitle(internship.getTitle());
        dto.setDescription(internship.getDescription());
        dto.setRequirements(internship.getRequirements());
        dto.setStartDate(internship.getStartDate());
        dto.setEndDate(internship.getEndDate());
        dto.setRestriction(internship.getRestrictions());

        dto.setCompanyName(internship.getCompany().getName());

        dto.setTechnologies(
                internship.getTechnologies()
                        .stream()
                        .map(InternshipTechnology::getTechnologyName)
                        .toList()
        );

        return dto;
    }
}
