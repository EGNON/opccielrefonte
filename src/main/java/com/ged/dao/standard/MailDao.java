package com.ged.dao.standard;

import com.ged.entity.standard.Mail;
import com.ged.entity.crm.RDV;
import com.ged.entity.standard.ModeleMsgAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailDao extends JpaRepository<Mail,Long> {
    void deleteByModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte);
}
