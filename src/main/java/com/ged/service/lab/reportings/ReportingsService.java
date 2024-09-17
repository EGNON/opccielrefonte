package com.ged.service.lab.reportings;

import com.ged.dao.lab.reportings.ReportingsDao;
import com.ged.dto.lab.reportings.RecensementOpClientOcasionnelDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingsService {
    private final ReportingsDao reportingsDao;

    public ReportingsService(ReportingsDao reportingsDao) {
        this.reportingsDao = reportingsDao;
    }

    public List<Object[]> recensementOpClientOcasionnel() {
        return reportingsDao.recensementOpClientOcassionnel();
    }
}
