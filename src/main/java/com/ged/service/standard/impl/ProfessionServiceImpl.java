package com.ged.service.standard.impl;

import com.ged.dao.standard.ProfessionDao;
import com.ged.dto.standard.ProfessionDto;
import com.ged.entity.standard.Profession;
import com.ged.mapper.standard.ProfessionMapper;
import com.ged.service.standard.ProfessionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;

    private final ProfessionDao professionDao;
    private final ProfessionMapper professionMapper;

    public ProfessionServiceImpl(ProfessionDao professionDao, ProfessionMapper professionMapper) {
        this.professionDao = professionDao;
        this.professionMapper = professionMapper;
    }

    @Override
    public Page<ProfessionDto> afficherProfessions(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleProfession");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Profession> professionPage=professionDao.findAll(pageRequest);
        return new PageImpl<>(professionPage.getContent().stream().map(professionMapper::deProfession).collect(Collectors.toList()),pageRequest,professionPage.getTotalElements());
    }

    @Override
    public List<ProfessionDto> afficherProfessions() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleProfession");
        return professionDao.findAll(sort).stream().map(professionMapper::deProfession).collect(Collectors.toList());
    }

    @Override
    public ProfessionDto afficher(Long id) {
        return professionMapper.deProfession(afficherProfessionSelonId(id));
    }

    @Override
    public ProfessionDto rechercherProfessionParLibelle(String libelle) {
        Profession profession=professionDao.findByLibelleProfession(libelle);
        if(profession!=null)
            return professionMapper.deProfession(profession);
        else
            return null;
    }

    @Override
    public Profession afficherProfessionSelonId(Long idProfession) {
        return professionDao.findById(idProfession).orElseThrow(()-> new com.ged.advice.EntityNotFoundException(Profession.class, "id", idProfession.toString()));
    }

    @Override
    public ProfessionDto creerProfession(ProfessionDto professionDto) {
        Profession profession=professionMapper.deProfessionDto(professionDto);
        Profession professionSave=professionDao.save(profession);
        return professionMapper.deProfession(professionSave);
    }

    @Override
    public ProfessionDto modifierProfession(ProfessionDto professionDto) {
        if(!professionDao.existsById(professionDto.getIdProf()))
            throw new com.ged.advice.EntityNotFoundException(Profession.class, "id", professionDto.getIdProf().toString());
        Profession profession = professionMapper.deProfessionDto(professionDto);
        Profession professionMaj = professionDao.save(profession);
        return professionMapper.deProfession(professionMaj);
    }

    @Override
    public List<Object> createProfessionFromOppciel1() {
        List<Object> result = new ArrayList<>();
        List<Object[]> categories;
        //Se connecter à opcciel1 et récupérer les différentes catégories
        try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Profession_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("libelleProfession", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            //Fournir les différents paramètres
            query.setParameter("libelleProfession", null);
            query.setParameter("numLigne", null);
            query.setParameter("dateCreationServeur", null);
            query.setParameter("dateDernModifServeur", null);
            query.setParameter("dateDernModifClient", null);
            query.setParameter("userLogin", null);
            query.setParameter("supprimer", false);
            query.setParameter("rowvers", null);
            query.execute();
            categories = query.getResultList();
            for (Object[] o: categories) {
                if(!professionDao.existsByLibelleProfession((String)o[0]))
                {
                    //System.out.println("ELEMENT 1 === " + o[0]);
                    Profession profession = new Profession();
                    profession.setLibelleProfession((String)o[0]);
                    result.add(professionDao.save(profession));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public void supprimerProfession(Long idProf) {
        professionDao.deleteById(idProf);
    }
}
