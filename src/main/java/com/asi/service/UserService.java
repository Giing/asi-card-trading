package com.asi.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.dto.LoginUserDto;
import com.asi.model.User;
import com.asi.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public String login(LoginUserDto userDto) {
		Optional<User> user = userRepository.findByEmailUser(userDto.email);
		
		if(user.isPresent() && user.get().getPasswordUser().equals(userDto.password)) {
			return createTokenFromUser(user.get());
		} else {
			return null;
		}
    }
	
	private String createTokenFromUser(User user) {
		return Jwts.builder()
			  .setIssuer("CardTrading")
			  .setSubject(user.getEmailUser())
			  .claim("fullName", user.getNameUser() + " " + user.getSurnameUser())
			  .claim("scope", "user")
			  .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
			  .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
			  .signWith(
			    SignatureAlgorithm.HS256,
			    TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
			  )
			  .compact();
	}
}
