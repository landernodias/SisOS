package com.com.nelcione.sisos.security;

import com.com.nelcione.sisos.domain.dtos.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager; //verifica se o usuario senha é valido
    private JWTUtil jwtUtil; //se altenticar gera um token

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    //tenta altenticar o usuario; tentativa de altenticar
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                 try{
                     CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class); //getInputStream: recupera o corpo da requisição
                     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
                     Authentication authentication = authenticationManager.authenticate(authenticationToken);
                     return authentication;
                 } catch(Exception e){
                    throw new RuntimeException(e);
                 }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException, ServletException {
            String username = ((UserSS) authResult.getPrincipal()).getUsername(); //pega o usuario
            String token = jwtUtil.generateToken(username); //gera um token
            response.setHeader("access-control-expose-headers", "Authorization");
             response.setHeader("Authorization", "Bearer " + token); //retornando o token para o usuartio
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
        throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{"
                    + "\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    +  "\"message\": \"Email ou senha inválidos\", "
                    + "\"path\": \"/login\"}";
        }
}
