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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null){
            throw new UsernameNotFoundException("회원이 아니거나 비밀번호가 일치하지 않습니다.");
        }

        List<GrantedAuthority> roles = user.getUserRoles()
                .stream().map(userRole -> userRole.getRole().getRoleName()).collect(Collectors.toSet())
                .stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

        UserContext userContext = new UserContext(user, roles);

        return userContext;
    }
}
