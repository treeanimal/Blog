package com.mycompany.white.security.service;

import com.mycompany.white.domain.entity.User;
import com.mycompany.white.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> roles = user.getUserRoles()
                .stream().map(userRole -> userRole.getRole().getRoleName()).collect(Collectors.toSet())
                .stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        UserContext userContext = new UserContext(user, roles);

        return userContext;
    }
}
