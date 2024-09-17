package com.ged.dao.security.token;

import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenDao  extends JpaRepository<Token, Integer> {
    @Query(value = "select t from Token t inner join Utilisateur u " +
      "on t.user.idPersonne = u.idPersonne where u.idPersonne = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Integer id);
    Optional<Token> findByToken(String token);
    List<Token> findAllByUser(Utilisateur user);
}
