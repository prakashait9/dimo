package com.dapperdrakes.dimo.config;

import com.dapperdrakes.dimo.service.JwtUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class JwtRequestFilterTest {

    @InjectMocks
    JwtRequestFilter jwtRequestFilter;

    @Mock
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoFilterInternalCorrectToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        UserDetails userDetails = mock(UserDetails.class);

        String token = "Bearer token";
        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn("test");

        when(jwtUserDetailsService.loadUserByUsername("test")).thenReturn(userDetails);

        when(jwtTokenUtil.validateToken(token, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, chain);

        verify(jwtUserDetailsService, atMostOnce()).loadUserByUsername("test");
        verify(jwtTokenUtil,atMostOnce()).validateToken(token, userDetails);
    }

    @Test
    public void testDoFilterInternalNullToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        UserDetails userDetails = mock(UserDetails.class);

        String token = "Bearer token";
        when(request.getHeader("Authorization")).thenReturn(null);
        when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(null);

        when(jwtUserDetailsService.loadUserByUsername("test")).thenReturn(userDetails);

        when(jwtTokenUtil.validateToken(token, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, chain);

        verify(jwtUserDetailsService, atMostOnce()).loadUserByUsername("test");
        verify(jwtTokenUtil,atMostOnce()).validateToken(token, userDetails);
    }
}