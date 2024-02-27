package com.example.springConstructionSecure;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

        MyUserRepository myUserRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            MyUser user = myUserRepository.findMyUserByUsername(username).orElseThrow(
                    ()->new UsernameNotFoundException("user not found"));

            return UserDetailsImpl.build(user);
        }

}