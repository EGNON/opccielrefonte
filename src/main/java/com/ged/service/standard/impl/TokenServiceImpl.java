package com.ged.service.standard.impl;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.security.Utilisateur;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.TokenService;
import com.ged.entity.security.token.Token;
import com.ged.dao.security.token.TokenDao;
import com.ged.dto.security.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    private final TokenDao tokenDao;
    private final UtilisateurMapper utilisateurMapper;

    public TokenServiceImpl(TokenDao tokenDao, UtilisateurMapper utilisateurMapper) {
        this.tokenDao = tokenDao;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        return null;
    }

    @Override
    public Token afficherSelonId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherSelonUser(UtilisateurDto utilisateurDto) {
        try {
            Utilisateur utilisateur = utilisateurMapper.deUtilisateurDto(utilisateurDto);
//            return ResponseHandler.generateResponse(
//                    "Liste de tous les dégrés",
//                    HttpStatus.OK,
//                    tokenDao.findAllByUser(utilisateur).stream().map(token -> {
//                        TokenResponse tokenResponse = new TokenResponse();
//                        BeanUtils.copyProperties(token, tokenResponse);
//                        return  tokenResponse;
//                    }).collect(Collectors.toList()));

            return ResponseHandler.generateResponse(
                    "Liste de tous les tokens",
                    HttpStatus.OK,
                    utilisateurDto);
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
    public ResponseEntity<Object> ajouter(TokenResponse tokenResponse) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifier(TokenResponse tokenResponse) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }
}
