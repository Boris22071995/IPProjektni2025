package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.Internship;
import com.etfbl.ip.BackendApp.model.requests.InternshipRequest;
import com.etfbl.ip.BackendApp.model.respons.InternshipResponse;

import java.util.List;

public interface InternshipService {

    public Internship save(InternshipRequest internshipRequest);

    public List<Internship> getAll();

    List<InternshipResponse> getAllAvailableInternships();
}
