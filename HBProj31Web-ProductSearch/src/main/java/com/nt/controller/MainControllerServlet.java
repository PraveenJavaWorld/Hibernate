package com.nt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.ProductDTO;
import com.nt.service.ProductMgmtService;
import com.nt.service.ProductMgmtServiceImpl;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {

    private ProductMgmtService service; 
	@Override
    public void init() throws ServletException {
    	service = new ProductMgmtServiceImpl();
    }  
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		ProductDTO dto = null;
		RequestDispatcher rd = null;
		//read form data
		id = Integer.parseInt(request.getParameter("pid"));
		try {
			//use service
			dto = service.fetchProduct(id);
			request.setAttribute("pDTO",dto);
			//forward to result.jsp
			rd = request.getRequestDispatcher("/result.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}//doGet(-,-)

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		service = null;
	}

}
