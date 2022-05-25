package com.asi.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.dto.LoginUserDto;
import com.asi.model.User;
import com.asi.repository.CardInstanceRepository;
import com.asi.repository.CardRepository;
import com.asi.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CardInstanceService cardInstanceService;

	@Autowired
	private HttpServletRequest request;
	
	public void addUser(User user) {
		user.setMoneyUser(1000.0);
		userRepository.save(user);
		cardInstanceService.giveCardsToNewUser(user);
		System.out.println("User created : " + user.getEmailUser());
	}
	
	public boolean isInDatabase(User user) {
		Optional<User> userFind = userRepository.findByEmailUser(user.getEmailUser());
		boolean userfds = userFind.isPresent();
		return userFind.isPresent();
	}
	
	public boolean isValidUserRegistration(User user) {
		boolean isValid = true;
		if(!this.isInDatabase(user)) {
		
			if(user.getNameUser() == null || user.getNameUser().isEmpty()) {
				isValid = false;
			}
			
			if(user.getSurnameUser() == null || user.getSurnameUser().isEmpty()) {
				isValid = false;
			}
		} else {
			isValid = false;
		}
		return isValid;
	}
	
	public String login(User user, String password) {
		if(BCrypt.checkpw(password, user.getPasswordUser())) {
			return createTokenFromUser(user);
		} else {
			return null;
		}
    }
	
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmailUser(email);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	public User getRequestUser() {
		String authToken = request.getHeader("Authorization");
		
		if(authToken == null || authToken.isEmpty()) {			
			return null;
		}
		
		String email = Jwts.parser()
				.setSigningKey(TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
				.parseClaimsJws(authToken)
				.getBody()
				.getSubject();
		
		return getUserByEmail(email);
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
