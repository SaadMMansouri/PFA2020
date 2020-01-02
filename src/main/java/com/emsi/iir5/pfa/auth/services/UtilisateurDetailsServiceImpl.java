package com.emsi.iir5.pfa.auth.services;

import com.emsi.iir5.pfa.dao.UtilisateurRepository;
import com.emsi.iir5.pfa.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

    private UtilisateurRepository userRepository;

    @Autowired
    public UtilisateurDetailsServiceImpl(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User: " + email + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("user")));
    }
}
