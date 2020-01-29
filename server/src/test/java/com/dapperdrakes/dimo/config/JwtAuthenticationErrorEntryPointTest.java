package com.dapperdrakes.dimo.config;

import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class JwtAuthenticationErrorEntryPointTest {

    @Test
    public void testCommence() throws IOException, ServletException {
        JwtAuthenticationErrorEntryPoint jwtAuthenticationErrorEntryPoint = new JwtAuthenticationErrorEntryPoint();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException e = mock(AuthenticationException.class);
        jwtAuthenticationErrorEntryPoint.commence(req,response, e);

        doNothing().when(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

        verify(response, atLeastOnce()).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}