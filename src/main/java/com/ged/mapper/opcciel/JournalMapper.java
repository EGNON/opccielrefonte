package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.JournalDto;
import com.ged.entity.opcciel.comptabilite.Journal;
import com.ged.projection.JournalProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class JournalMapper {
    private final PlanMapper planMapper;

    public JournalMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public JournalDto deJournal(Journal journal)
    {
        JournalDto journalDto = new JournalDto();
        BeanUtils.copyProperties(journal, journalDto);
        if(journal.getPlan()!=null)
        {
            journalDto.setPlan(planMapper.dePlan(journal.getPlan()));
        }
        return journalDto;
    }
    public JournalDto deJournalProjection(JournalProjection journal)
    {
        JournalDto journalDto = new JournalDto();
        BeanUtils.copyProperties(journal, journalDto);
        if(journal.getPlan()!=null) {
            journalDto.setPlan(planMapper.dePlan(journal.getPlan()));
        }
        return journalDto;
    }

    public Journal deJournalDto(JournalDto journalDto)
    {
        Journal journal = new Journal();
        BeanUtils.copyProperties(journalDto, journal);
        if(journalDto.getPlan()!=null)
        {
            journal.setPlan(planMapper.dePlanDto(journalDto.getPlan()));
        }
        return journal;
    }
}
