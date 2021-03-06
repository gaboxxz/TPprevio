package com.utn.dds.tpprevio.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utn.dds.tpprevio.repository.impl.UsuarioRepositoryImpl;
import com.utn.dds.tpprevio.service.UsuarioService;
import com.utn.dds.tpprevio.service.impl.UsuarioServiceImpl;
import com.utn.dds.tpprevio.domain.Usuario;

public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsuarioService usuarioService = new UsuarioServiceImpl(new UsuarioRepositoryImpl());
	
    public UsuarioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = (HttpSession) request.getSession();	
		String username = (String) httpSession.getAttribute("username");
		
		if(username == null) {
			response.sendRedirect("login.jsp");
		} else {
			String password = request.getParameter("password");	
			usuarioService.cambiarPassword(username, password);
			request.setAttribute("mensajeOk", "Su contraseņa ha sido cambiada correctamente.");
			request.getRequestDispatcher("/WEB-INF/bienvenido.jsp").forward(request, response);
		}
	}

}
