package controller;

import dao.FarmCertificateDAO;
import model.FarmCertificate;

import java.util.ArrayList;
import java.util.List;

public class SearchFarmCertificate {

    public List<FarmCertificate> searchByType(String type) {
        List<FarmCertificate> resultList = new ArrayList<>();
        for (FarmCertificate cert : FarmCertificateDAO.getInstance().selectAll()) {
            if (cert.getCertificateType().toLowerCase().contains(type.toLowerCase())) {
                resultList.add(cert);
            }
        }
        return resultList;
    }

    public List<FarmCertificate> searchByIssueDate(String issueDate) {
        List<FarmCertificate> resultList = new ArrayList<>();
        for (FarmCertificate cert : FarmCertificateDAO.getInstance().selectAll()) {
            if (cert.getIssueDate().equals(issueDate)) {
                resultList.add(cert);
            }
        }
        return resultList;
    }

    public List<FarmCertificate> searchByStatus(int status) {
        List<FarmCertificate> resultList = new ArrayList<>();
        for (FarmCertificate cert : FarmCertificateDAO.getInstance().selectAll()) {
            if (cert.getStatus() == status) {
                resultList.add(cert);
            }
        }
        return resultList;
    }
}
