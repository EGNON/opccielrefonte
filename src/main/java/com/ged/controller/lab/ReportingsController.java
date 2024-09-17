package com.ged.controller.lab;

import com.ged.dao.lab.reportings.ReportingsDao;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.lab.reportings.TransactionSuspecteInhabituelleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reportings")
public class ReportingsController {
    private final ReportingsDao reportingsDao;

    public ReportingsController(ReportingsDao reportingsDao) {
        this.reportingsDao = reportingsDao;
    }

    @GetMapping("/recensementopclient/liste")
    public List<Object[]> recensementOpClient() {
        return reportingsDao.recensementOpClientOcassionnel();
    }

    @PostMapping("/transactionsuspectinhabituel/liste")
    public List<Object[]> transactionsuspectinhabituel(@RequestBody TransactionSuspecteInhabituelleDto transactionSuspecteInhabituelleDto) {
        return reportingsDao.transactionSuspectInhabituel(transactionSuspecteInhabituelleDto);
    }

    @GetMapping("/suivi-client/sanctionne/liste")
    public List<Object[]> suiviClientSanction() {
        return reportingsDao.suiviClientSanction();
    }

    /*@GetMapping("/registre/confidentiel/startDate/{startDate}/endDate/{endDate}")
    public List<Object[]> registreConfidentiel(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
        return reportingsDao.registreConfidentiel(startDate, endDate);*/

    @PostMapping("/registre/confidentiel")
    public List<Object[]> registreConfidentiel(@RequestBody BeginEndDateParameter beginEndDateParameter) {
        return reportingsDao.registreConfidentiel(beginEndDateParameter);
    }

    @PostMapping(value = {"/risque/volatilite/fcp", "/risque/volatilite/fcp/{idOpcvm}"})
    public List<Object[]> volatilite(@PathVariable(required = false) Long idOpcvm, @RequestBody BeginEndDateParameter beginEndDateParameter) {
        if (idOpcvm != null) {
            return reportingsDao.volatilite(idOpcvm, beginEndDateParameter);
        } else {
            return reportingsDao.volatilite(null, beginEndDateParameter);
        }
    }
}
