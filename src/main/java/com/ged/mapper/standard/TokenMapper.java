package com.ged.mapper.standard;

import com.ged.entity.security.token.Token;
import com.ged.dto.security.TokenResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenMapper {
    public TokenResponse deToken(Token token)
    {
        TokenResponse tokenResponse = new TokenResponse();
        BeanUtils.copyProperties(token, tokenResponse);
        return tokenResponse;
    }

    public Token deTokenResponse(TokenResponse tokenResponse)
    {
        Token token = new Token();
        BeanUtils.copyProperties(tokenResponse, token);
        return token;
    }
}
