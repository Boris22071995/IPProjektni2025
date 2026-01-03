package com.etfbl.ip.BackendApp.service;

import com.etfbl.ip.BackendApp.model.requests.CvRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CvService {

    void createCv(CvRequest request, MultipartFile image);
}
