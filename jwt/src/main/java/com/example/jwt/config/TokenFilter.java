package com.example.jwt.config;

import com.example.jwt.data.JwtAuthentication;
import com.example.jwt.service.TokenService;
import com.example.jwt.util.jwtUtil.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";

    private final TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {
        final String token = getTokenFromRequest((HttpServletRequest) request);// достаем токен из header
        if (token != null && tokenService.validateAccessToken(token)) { // проверяем наличие токена и валидируем его
            final Claims claims = tokenService.getClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims); // собираем объект, чтобы положить в контекст
            jwtInfoToken.setAuthenticated(true); // отмечаем как аутентифицированный
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        fc.doFilter(request, response);
    }

    /**
     * Получаем токен из хедера "Authorization"
     *
     * @param request содержит токен в хедере
     * @return jwt-токен
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
