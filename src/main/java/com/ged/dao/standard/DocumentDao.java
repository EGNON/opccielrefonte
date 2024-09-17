package com.ged.dao.standard;

import com.ged.entity.crm.CompteRendu;
import com.ged.entity.standard.Document;
import com.ged.entity.crm.RDV;
import com.ged.entity.standard.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentDao extends JpaRepository<Document,Long> {
    List<Document> findByRdv(RDV rdv);
    List<Document> findByCompteRendu(CompteRendu compteRendu);
    List<Document> findByPersonne(Personne personne);
    void deleteByRdv(RDV rdv);
}
