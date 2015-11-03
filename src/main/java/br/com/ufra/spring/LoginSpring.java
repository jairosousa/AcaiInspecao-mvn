/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.spring;

import br.com.ufra.entidade.Tecnico;
import br.com.ufra.rn.TecnicoRN;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Jairo
 */
public class LoginSpring implements UserDetailsService {

    private final TecnicoRN tecnicoRN = new TecnicoRN();

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        if (string.isEmpty()) {
            throw new UsernameNotFoundException(string);
        }
        Tecnico usuarioLogado;
        
        try {
            usuarioLogado = tecnicoRN.obterEmail(string);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException(string, e);
        }
        
        List<GrantedAuthority> papeis = new ArrayList<>();
        if (usuarioLogado != null) {
            papeis.add(new GrantedAuthorityImpl("ROLE_" + "USER"));
            User user = new User(
                    usuarioLogado.getEmail(),
                    usuarioLogado.getSenha(),
                    true,
                    true,
                    true,
                    true,
                    papeis);
            return user;
        } else {
            throw new UsernameNotFoundException(string);
        }
    }

}
