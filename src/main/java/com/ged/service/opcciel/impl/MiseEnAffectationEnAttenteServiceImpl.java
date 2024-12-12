package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.MiseEnAffectationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiseEnAffectationEnAttenteServiceImpl implements MiseEnAffectationService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
   private final LibraryDao libraryDao;

    public MiseEnAffectationEnAttenteServiceImpl(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    @Override
    public ResponseEntity<Object> verifierMiseEnAffectationEnAttente(Long idOpcvm) {
        try {
            List<Object[]> list=libraryDao.verificationMiseAffectationEnAttente(idOpcvm);
            return ResponseHandler.generateResponse(
                    "vérification mise affectation en attente",
                    HttpStatus.OK,
                    list.size());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
