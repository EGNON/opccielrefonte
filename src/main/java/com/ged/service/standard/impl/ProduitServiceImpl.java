package com.ged.service.standard.impl;

import com.ged.dao.standard.ProduitDao;
import com.ged.dto.standard.ProduitDto;
import com.ged.entity.standard.Produit;
import com.ged.mapper.standard.ProduitMapper;
import com.ged.service.standard.ProduitService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private final ProduitDao produitDao;
    private final ProduitMapper produitMapper;

    public ProduitServiceImpl(ProduitDao produitDao, ProduitMapper produitMapper) {
        this.produitDao = produitDao;
        this.produitMapper = produitMapper;
    }

    @Override
    public Page<ProduitDto> afficherProduits(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"designation");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Produit> produitPage=produitDao.findAll(pageRequest);
        return new PageImpl<>(produitPage.getContent().stream().map(produitMapper::deProduit).collect(Collectors.toList()),pageRequest,produitPage.getTotalElements());
    }

    @Override
    public List<ProduitDto> afficherProduits() {
        Sort sort=Sort.by(Sort.Direction.ASC,"designation");
        return produitDao.findAll(sort).stream().map(produitMapper::deProduit).collect(Collectors.toList());
    }

    @Override
    public Produit afficherProduitSelonId(long idProduit) {
        return produitDao.findById(idProduit).orElseThrow(()-> new EntityNotFoundException("Produit avec ID "+idProduit+" est introuvable"));
    }

    @Override
    public ProduitDto afficherProduit(long idProduit) {
        return produitMapper.deProduit(afficherProduitSelonId(idProduit));
    }

    @Override
    public ProduitDto rechercherProduitParDesignation(String designation) {
        Produit produit=produitDao.findByDesignation(designation);
        if(produit!=null)
            return produitMapper.deProduit(produit);
        else
            return null;
    }

    @Override
    public ProduitDto creerProduit(ProduitDto produitDto) {
        Produit produit = produitMapper.deProduitDto(produitDto);
        Produit produitSave = produitDao.save(produit);
        return produitMapper.deProduit(produitSave);
    }

    @Override
    public ProduitDto modifierProduit(ProduitDto produitDto) {
        Produit produit=produitMapper.deProduitDto(produitDto);
        Produit produitSave=produitDao.save(produit);
        return produitMapper.deProduit(produitSave);
    }

    @Override
    public void supprimerProduit(long idProduit) {
        produitDao.deleteById(idProduit);
    }
}
