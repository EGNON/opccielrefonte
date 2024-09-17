package com.ged.dao.standard;

import com.ged.entity.standard.CleDocumentMail;
import com.ged.entity.standard.DocumentMail;
import com.ged.entity.standard.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentMailDao extends JpaRepository<DocumentMail, CleDocumentMail> {
    List<DocumentMail> findByMail(Mail mail);
    void deleteByMail(Mail mail);
}
