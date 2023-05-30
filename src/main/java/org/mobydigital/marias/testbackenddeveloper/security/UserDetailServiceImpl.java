package org.mobydigital.marias.testbackenddeveloper.security;

import org.mobydigital.marias.testbackenddeveloper.model.entity.User;
import org.mobydigital.marias.testbackenddeveloper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRepository.save(new User(1L,"marias","marias@mobydigital.com","$2a$10$ZerBpL7mQGnuk3rvp1Nhz.keTiKFztJyMBidhl8Y1DyqCEeZ1XERu"));
        User user = userRepository.findOneByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not exist"));
        return new UserDetailsImpl(user);
    }
}
