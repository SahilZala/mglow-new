package com.mark2.mglow.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mark2.mglow.helper.JwtUtil;
import com.mark2.mglow.service.CustomeUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private CustomeUserDetailsService customeDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization"); 
		String username = null;
		String jwtToken = null;

		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
				UserDetails userDetails = this.customeDetailsService.loadUserByUsername(username);
				
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource());
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
				else {
					System.out.println("JWTAUTHENTICATIONFILTER log: token is not valid");
					StringBuilder sb = new StringBuilder();
		            sb.append("{ ");
		            sb.append("\"error\": \"token is not valid\",");
		            sb.append("\"message\": \"Invalid Token.\",");
		            sb.append("\"path\": \"")
		            .append(request.getRequestURL())
		            .append("\"");
		            sb.append("} ");
		            response.setContentType("application/json");
		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		            response.getWriter().write(sb.toString());
		            return;
				}
			
			}
			catch(Exception ex) {
				System.out.println("JWTAUTHENTICATIONFILTER log: "+ex.getMessage());
				StringBuilder sb = new StringBuilder();
	            sb.append("{ ");
	            sb.append("\"error\": \""+ex.getMessage()+"\",");
	            sb.append("\"message\": \"Invalid Token.\",");
	            sb.append("\"path\": \"")
	            .append(request.getRequestURL())
	            .append("\"");
	            sb.append("} ");
	            response.setContentType("application/json");
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write(sb.toString());
	            
	            return;
			}
		
		}
		else {
			System.out.println("JWTAUTHENTICATIONFILTER log: an unauthorized request");
			
			StringBuilder sb = new StringBuilder();
            sb.append("{ ");
            sb.append("\"error\": \"unauthorized request\",");
            sb.append("\"message\": \"unauthorized\",");
            sb.append("\"path\": \"")
            .append(request.getRequestURL())
            .append("\"");
            sb.append("} ");
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(sb.toString());
            
            return;

		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
