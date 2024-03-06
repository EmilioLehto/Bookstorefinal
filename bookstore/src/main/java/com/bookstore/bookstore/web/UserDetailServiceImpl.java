package com.bookstore.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.bookstore.bookstore.Book.User;
import com.bookstore.bookstore.Book.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService  {
	
	@Autowired
	UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User currentuser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currentuser.getRole()));
        return user;
    }    
    
}
