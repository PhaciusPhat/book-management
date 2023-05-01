package com.microservice.secure.security;

import com.microservice.secure.entity.Account;
import com.microservice.secure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailImplService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if(account != null){
            List<GrantedAuthority> authorities =  new ArrayList<>();
            return new User(account.getUsername(), account.getPassword(), authorities);
        } else{
            throw new UsernameNotFoundException("user not exist");
        }
    }
}
