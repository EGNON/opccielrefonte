package com.ged.controller.lab;

import com.ged.dto.lab.SanctionsONUDto;
import com.ged.dto.lab.TypeCritereDto;
import com.ged.entity.lab.SanctionsONU;
import com.ged.service.lab.SanctionONUService;
import com.ged.service.lab.TypeCritereService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanctiononus")
public class SanctionONUController {
    private final SanctionONUService sanctionONUService;

    public SanctionONUController(SanctionONUService sanctionONUService) {
        this.sanctionONUService = sanctionONUService;
    }


    @PostMapping
    public ResponseEntity<Object> liste(@RequestBody String[] sanctionsONUDtos)
    {
        return sanctionONUService.listeSanctionONU(sanctionsONUDtos);
    }


}
