package com.ged.service.standard.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.CategoriePersonneDao;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.entity.standard.CategoriePersonne;
import com.ged.mapper.standard.CategoriePersonneMapper;
import com.ged.service.standard.CategoriePersonneService;
import jakarta.persistence.*;
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
public class CategoriePersonneServiceImpl implements CategoriePersonneService {
    @PersistenceContext
    private EntityManager emRefonte;

//    @PersistenceContext
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;

    private final CategoriePersonneDao categoriePersonneDao;
    private final CategoriePersonneMapper categoriePersonneMapper;

    public CategoriePersonneServiceImpl(CategoriePersonneDao categoriePersonneDao, CategoriePersonneMapper categoriePersonneMapper) {
        this.categoriePersonneDao = categoriePersonneDao;
        this.categoriePersonneMapper = categoriePersonneMapper;
    }

    @Override
    public Object[] get() {
        try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[dbo].[GET_TOTAL_CAT_BY_LIBELLE]")
                    .registerStoredProcedureParameter(
                            "libelle",
                            String.class,
                            ParameterMode.IN
                    )
                    .setParameter("libelle", "ETUDIANT");
            query.execute();
            return query.getResultList().toArray();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<CategoriePersonneDto> afficherCategoriePersonnes(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<CategoriePersonne> categoriePersonnePage = categoriePersonneDao.findAll(pageRequest);

        return new PageImpl<>(categoriePersonnePage.getContent().stream().map(categoriePersonneMapper::deCatPersonne).collect(Collectors.toList()), pageRequest, categoriePersonnePage.getTotalElements());
    }

    @Override
    public List<CategoriePersonneDto> afficherCategoriePersonnes() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        return categoriePersonneDao.findAll(sort).stream().map(categoriePersonneMapper::deCatPersonne).collect(Collectors.toList());
    }

    @Override
    public CategoriePersonneDto afficher(Long id) {
        return categoriePersonneMapper.deCatPersonne(afficherCategoriePersonneSelonId(id));
    }

    @Override
    public CategoriePersonne afficherCategoriePersonneSelonId(Long idCategorie) {
        return categoriePersonneDao.findById(idCategorie).orElseThrow(() -> new EntityNotFoundException(CategoriePersonne.class, "id", idCategorie.toString()));
    }

    @Override
    public CategoriePersonneDto rechercherCategoriePersonneParLibelle(String libelle) {
        CategoriePersonne categoriePersonne=categoriePersonneDao.findByLibelle(libelle);
        if(categoriePersonne!=null)
            return categoriePersonneMapper.deCatPersonne(categoriePersonne);
        else
            return null;
    }

    @Override
    public CategoriePersonneDto creerCategoriePersonne(CategoriePersonneDto categoriePersonneDto) throws JsonProcessingException {
        CategoriePersonne categoriePersonne = categoriePersonneMapper.deCatPersonneDto(categoriePersonneDto);
        categoriePersonne = categoriePersonneDao.save(categoriePersonne);
        return categoriePersonneMapper.deCatPersonne(categoriePersonne);
    }

    @Override
    public CategoriePersonneDto modifierCategoriePersonne(CategoriePersonneDto categoriePersonneDto) {
        if(!categoriePersonneDao.existsById(categoriePersonneDto.getIdCategorie()))
            throw new EntityNotFoundException(CategoriePersonne.class, "id", categoriePersonneDto.getIdCategorie().toString());
        CategoriePersonne categoriePersonne = categoriePersonneMapper.deCatPersonneDto(categoriePersonneDto);
        categoriePersonne = categoriePersonneDao.save(categoriePersonne);
        return categoriePersonneMapper.deCatPersonne(categoriePersonne);
    }

    @Override
    public List<Object> createCategorieFromOppciel1() {
        List<Object> result = new ArrayList<>();
        List<Object[]> categories;
        //Se connecter à opcciel1 et récupérer les différentes catégories
        try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_Categorie_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("libelleCategorie", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("reference", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("typePersonne", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            //Fournir les différents paramètres
            query.setParameter("libelleCategorie", null);
            query.setParameter("reference", null);
            query.setParameter("typePersonne", null);
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
                if(!categoriePersonneDao.existsByLibelle((String)o[0]))
                {
                    //System.out.println("ELEMENT 1 === " + o[0]);
                    CategoriePersonne cat = new CategoriePersonne();
                    cat.setLibelle((String)o[0]);
                    result.add(categoriePersonneDao.save(cat));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public void supprimerCategoriePersonne(Long id) {
        categoriePersonneDao.deleteById(id);
    }
}
