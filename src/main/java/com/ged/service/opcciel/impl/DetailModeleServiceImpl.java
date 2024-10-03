package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.FormuleDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.dao.opcciel.comptabilite.DetailModeleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.DetailModeleDto;
import com.ged.entity.opcciel.comptabilite.CleDetailModele;
import com.ged.entity.opcciel.comptabilite.Formule;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.DetailModele;
import com.ged.mapper.opcciel.DetailModeleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.DetailModeleService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DetailModeleServiceImpl implements DetailModeleService {
    private final DetailModeleDao DetailModeleDao;
    private final ModeleEcritureDao modeleEcritureDao;
    private final FormuleDao formuleDao;
    private final DetailModeleMapper DetailModeleMapper;

    public DetailModeleServiceImpl(DetailModeleDao DetailModeleDao, ModeleEcritureDao modeleEcritureDao, FormuleDao formuleDao, DetailModeleMapper DetailModeleMapper) {
        this.DetailModeleDao = DetailModeleDao;
        this.modeleEcritureDao = modeleEcritureDao;
        this.formuleDao = formuleDao;
        this.DetailModeleMapper = DetailModeleMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numDetailModele");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<DetailModele> DetailModelePage;
           // if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            //{
              //  DetailModelePage = DetailModeleDao.findByLibelleDetailModeleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            //}
            //else {
                DetailModelePage = DetailModeleDao.findAll(pageable);
            //}
            List<DetailModeleDto> content = DetailModelePage.getContent().stream().map(DetailModeleMapper::deDetailModele).collect(Collectors.toList());
            DataTablesResponse<DetailModeleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DetailModelePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DetailModelePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des modèles formules par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"numDetailModele");
            return ResponseHandler.generateResponse(
                    "Liste de tous les modèles formules",
                    HttpStatus.OK,
                    DetailModeleDao.findAll(sort).stream().map(DetailModeleMapper::deDetailModele).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public DetailModele afficherSelonId(CleDetailModele idDetailModele) {
        return DetailModeleDao.findById(idDetailModele).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficherSelonModeleEcriture(String codeModeleEcriture) {
        try {
            ModeleEcriture modeleEcriture=modeleEcritureDao.findById(codeModeleEcriture).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Details modèle selon code modele ecriture= " + codeModeleEcriture,
                    HttpStatus.OK,
                    DetailModeleDao.findByModeleEcritureAndSupprimerOrderByModeleEcritureAscSensMvtDescNumeroOrdreAsc(modeleEcriture,false).stream().map(DetailModeleMapper::deDetailModele).collect(Collectors.toList()));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficher(CleDetailModele codeDetailModele) {
        try {
            return ResponseHandler.generateResponse(
                    "Modele formule dont id = " + codeDetailModele,
                    HttpStatus.OK,
                    DetailModeleMapper.deDetailModele(afficherSelonId(codeDetailModele)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(DetailModeleDto DetailModeleDto) {
        try {
            DetailModeleDto.setSupprimer(false);
            DetailModele DetailModele = DetailModeleMapper.deDetailModeleDto(DetailModeleDto);
            CleDetailModele cleDetailModele=new CleDetailModele();
            cleDetailModele.setNumeroOrdre(DetailModeleDto.getNumeroOrdre());
            cleDetailModele.setNumCompteComptable(DetailModeleDto.getNumCompteComptable());
            cleDetailModele.setCoodeModeleEcriture(DetailModeleDto.getModeleEcriture().getCodeModeleEcriture());
            DetailModele.setIdDetailModele(cleDetailModele);
            ModeleEcriture modeleEcriture=new ModeleEcriture();
            Formule formule=new Formule();
            if(DetailModeleDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(DetailModeleDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                DetailModele.setModeleEcriture(modeleEcriture);
            }
            if(DetailModeleDto.getFormule()!=null){
                formule=formuleDao.findById(DetailModeleDto.getFormule().getIdFormule()).orElseThrow();
                DetailModele.setFormule(formule);
            }
            DetailModele = DetailModeleDao.save(DetailModele);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    DetailModeleMapper.deDetailModele(DetailModele));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(DetailModeleDto[] DetailModeleDto) {
        try {
            for(DetailModeleDto modeleDto:DetailModeleDto)
            {
               creer(modeleDto);
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DetailModeleDto DetailModeleDto) {
        try {
            DetailModeleDto.setSupprimer(false);
            DetailModele DetailModele = DetailModeleMapper.deDetailModeleDto(DetailModeleDto);

            CleDetailModele cleDetailModele=new CleDetailModele();
            cleDetailModele.setNumeroOrdre(DetailModeleDto.getNumeroOrdre());
            cleDetailModele.setNumCompteComptable(DetailModeleDto.getNumCompteComptable());
            cleDetailModele.setCoodeModeleEcriture(DetailModeleDto.getModeleEcriture().getCodeModeleEcriture());
            DetailModele.setIdDetailModele(cleDetailModele);


            ModeleEcriture modeleEcriture=new ModeleEcriture();
            Formule formule=new Formule();
            if(DetailModeleDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(DetailModeleDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                DetailModele.setModeleEcriture(modeleEcriture);
            }
            if(DetailModeleDto.getFormule()!=null){
                formule=formuleDao.findById(DetailModeleDto.getFormule().getIdFormule()).orElseThrow();
                DetailModele.setFormule(formule);
            }
            DetailModele = DetailModeleDao.save(DetailModele);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    DetailModeleMapper.deDetailModele(DetailModele));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleDetailModele codeDetailModele) {
        try {
            DetailModeleDao.deleteById(codeDetailModele);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public void supprimerSelonModeleEcriture(String codeModeleEcriture) {
        try {
            ModeleEcriture modeleEcriture=modeleEcritureDao.findById(codeModeleEcriture).orElseThrow();
            DetailModeleDao.deleteByModeleEcriture(modeleEcriture);
            System.out.println("Suppression effectuée avec succès");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
