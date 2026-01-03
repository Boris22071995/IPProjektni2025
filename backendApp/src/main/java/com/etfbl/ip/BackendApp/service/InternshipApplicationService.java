package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.requests.InternshipApplicationRequest;

public interface InternshipApplicationService {
    void apply(InternshipApplicationRequest request);
}
