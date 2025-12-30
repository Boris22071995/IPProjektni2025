package net.etfbl.ip.backendApp.service;

import net.etfbl.ip.backendApp.model.Intership;

import java.util.List;

public interface IntershipService {

    Intership save(Intership intership);

    Intership update(Long id, Intership intership);

    List<Intership> findAll();

    List<Intership> findByCompany(Long companyId);
}
