package com.placement.servlet;

import com.placement.dao.CompanyDAO;
import com.placement.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company")
public class CompanyServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyDAO companyDAO;

    public void init() {
        companyDAO = new CompanyDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String companyName = request.getParameter("companyName");
        
        Company company = new Company();
        company.setCompanyName(companyName);
        
        int companyId = companyDAO.addCompany(company);
        
        if (companyId > 0) {
            request.setAttribute("message", "Company added successfully.");
        } else {
            request.setAttribute("error", "Failed to add company.");
        }
        request.getRequestDispatcher("/addCompany.jsp").forward(request, response);
    }
}