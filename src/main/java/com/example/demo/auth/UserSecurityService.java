package com.example.demo.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<com.example.demo.entity.User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
    
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        if("admin".equals(userOptional.get().getUsername()))
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        else
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));

        return new User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorities);
    }
}
