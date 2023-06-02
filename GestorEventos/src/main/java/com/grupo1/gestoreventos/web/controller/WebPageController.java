package com.grupo1.gestoreventos.web.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo1.gestoreventos.model.dto.UsuarioDTO;
import com.grupo1.gestoreventos.service.UsuarioService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebPageController {

	private static final Logger log = LoggerFactory.getLogger(WebPageController.class);
	
	@Autowired
	UsuarioService usuarioService;
	
	
	
	
	//Este tienes que ser sustituido por se
	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView mav = new ModelAndView("app/user");
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(5L);
		
		mav.addObject("usuarioDTO",usuarioService.findById(usuarioDTO));
		
		return mav;
		
	
	}
	@GetMapping("/admin")
	public String admin() {
		return "app/admin";
	}
	
	@GetMapping("/userType")
	public ModelAndView userType(@AuthenticationPrincipal User user, HttpServletResponse response, HttpServletRequest request) {
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
        	ModelAndView mav = new ModelAndView("app/admin");
            return mav;
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
        	UsuarioDTO usuarioDTO = new UsuarioDTO();
        	usuarioDTO.setNombreUsuario(user.getUsername());
        	usuarioDTO = usuarioService.findByUsername(usuarioDTO);
        	HttpSession session = request.getSession();
            
            // Obtener el Java Session ID
            String sessionId = session.getId();
            usuarioDTO.setCookie(sessionId);
            usuarioService.saveCookie(usuarioDTO);
            
        	ModelAndView mav = new ModelAndView("app/user");
        	mav.addObject("usuarioDTO", usuarioDTO);
            return mav;
        }
        return null;
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Realizar cualquier lógica adicional antes del cierre de sesión

        // Invalidar la sesión actual
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Limpiar la autenticación actual
        SecurityContextHolder.clearContext();
        
        // Redirigir al inicio o a otra página de tu elección después del cierre de sesión
        return "redirect:/";
    }


	


}
