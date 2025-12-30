package net.etfbl.ip.backendApp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.etfbl.ip.backendApp.model.Intership;
import net.etfbl.ip.backendApp.repository.IntershipRepository;
import net.etfbl.ip.backendApp.service.IntershipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntershipServiceImpl implements IntershipService {

    private final IntershipRepository intershipRepository;

    @Override
    public Intership save(Intership intership) {
        return intershipRepository.save(intership);
    }

    @Override
    public Intership update(Long id, Intership intership) {
        Intership existing = intershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        existing.setTitle(intership.getTitle());
        existing.setDescription(intership.getDescription());
        existing.setStartDate(intership.getStartDate());
        existing.setEndDate(intership.getEndDate());
        existing.setRequirements(intership.getRequirements());

        return intershipRepository.save(existing);
    }

    @Override
    public List<Intership> findAll() {
        return intershipRepository.findAll();
    }

    @Override
    public List<Intership> findByCompany(Long companyId) {
        return intershipRepository.findByCompanyId(companyId);
    }
}
