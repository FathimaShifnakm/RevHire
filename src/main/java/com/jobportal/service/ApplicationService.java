package com.jobportal.service;

import com.jobportal.dao.ApplicatoinDAO;
import com.jobportal.model.Application;

public class ApplicationService {
    private ApplicatoinDAO applicatoinDAO;

    public ApplicationService(ApplicatoinDAO applicatoinDAO) {
        this.applicatoinDAO = applicatoinDAO;
    }


    public void applyForJob(Application application) {
        applicatoinDAO.applyForJob(application);
    }
}
