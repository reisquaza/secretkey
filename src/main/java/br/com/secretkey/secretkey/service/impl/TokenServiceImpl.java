package br.com.secretkey.secretkey.service.impl;

import br.com.secretkey.secretkey.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private static final String secret = "AAAAA";

    public String createToken(String name, String value) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date expirationDate = calendar.getTime();

        return JWT.create()
                .withIssuer("secretkey")
                .withClaim(name, value)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public UUID retrieveUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verifier = JWT.require(algorithm).withIssuer("secretkey").build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return UUID.fromString(decodedJWT.getClaim("userId").asString());
    }

}
