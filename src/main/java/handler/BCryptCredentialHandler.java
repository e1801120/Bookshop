package handler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptCredentialHandler implements org.apache.catalina.CredentialHandler{

	@Override
	public boolean matches(String inputCredentials, String storedCredentials) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		return passwordEncoder.matches(inputCredentials, storedCredentials);
	}

	@Override
	public String mutate(String inputCredentials) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 		
		String encoded = passwordEncoder.encode(inputCredentials);
		return encoded;
	}
}