package com.toyproject.discordclone.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toyproject.discordclone.dto.USERDto;
import com.toyproject.discordclone.model.DefaultResponse;
import com.toyproject.discordclone.service.USERService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private USERService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(path.startsWith("/api/user/signin") || path.startsWith("/api/user/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        String email = null;
        String token = null;
        HttpSession session = request.getSession();

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
        }
        email = jwtUtil.extractUserEmail(token);
        if(email == null){
            exceptionCall(response, "invalidToken");
            return;
        }
        USERDto user = userService.getUserByEmail(email);
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            session.setAttribute("userId", email);
        }

        filterChain.doFilter(request, response);
    }

    private HttpServletResponse exceptionCall(HttpServletResponse response, String errorType) throws IOException {
        DefaultResponse res = new DefaultResponse();
        if (errorType.equals("invalidToken")) {
            res.setSuccess(false);
            res.setMsg("InvalidToken");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(res));
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        return response;
    }
}
