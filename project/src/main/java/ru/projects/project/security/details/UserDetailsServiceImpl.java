package ru.egprojects.sw1_springboot.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.egprojects.sw1_springboot.model.AuthData;
import ru.egprojects.sw1_springboot.repository.AuthDataRepository;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthDataRepository authDataRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AuthData> authDataOptional = authDataRepository.findByEmail(email);
        if (authDataOptional.isPresent()) {
            return new UserDetailsImpl(authDataOptional.get());
        } else {
            throw new UsernameNotFoundException("User with <" + email + "> not found");
        }
    }
}
