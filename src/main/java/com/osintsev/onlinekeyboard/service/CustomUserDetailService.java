package com.osintsev.onlinekeyboard.service;




import com.osintsev.onlinekeyboard.model.CustomUserDetail;
import com.osintsev.onlinekeyboard.model.User;
import com.osintsev.onlinekeyboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(s);
        user.orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.map(CustomUserDetail::new).get();
    }

}
