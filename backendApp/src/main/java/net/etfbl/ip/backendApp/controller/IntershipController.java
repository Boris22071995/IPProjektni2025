package net.etfbl.ip.backendApp.controller;


import lombok.RequiredArgsConstructor;
import net.etfbl.ip.backendApp.model.Intership;
import net.etfbl.ip.backendApp.service.IntershipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class IntershipController {

    private final IntershipService intershipService;

    @PostMapping
    public Intership create(@RequestBody Intership intership) {
        return intershipService.save(intership);
    }

    @PutMapping("/{id}")
    public Intership update(@PathVariable Long id, @RequestBody Intership intership) {
        return intershipService.update(id, intership);
    }

    @GetMapping
    public List<Intership> getAll() {
        return intershipService.findAll();
    }

    @GetMapping("/company/{companyId}")
    public List<Intership> getByCompany(@PathVariable Long companyId) {
        return intershipService.findByCompany(companyId);
    }
}
