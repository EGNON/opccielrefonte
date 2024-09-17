package com.ged.dao.standard;

import com.ged.entity.standard.CleEnvoiMail;
import com.ged.entity.standard.EnvoiMail;
import com.ged.entity.standard.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvoiMailDao extends JpaRepository<EnvoiMail, CleEnvoiMail> {
    List<EnvoiMail> findByMail(Mail mail);
    void deleteByMail(Mail mail);
}
