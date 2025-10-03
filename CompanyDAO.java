package com.placement.dao;

import com.placement.model.Company;
import com.placement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    /**
     * Adds a new company to the database.
     * @param company The Company object containing the company's name.
     * @return The auto-generated ID of the new company, or -1 if the operation fails.
     */
    public int addCompany(Company company) {
        String sql = "INSERT INTO companies (company_name) VALUES (?)";
        int companyId = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, company.getCompanyName());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                companyId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyId;
    }

    /**
     * Retrieves all companies from the database.
     * @return A list of all Company objects, ordered by company name.
     */
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM companies ORDER BY company_name";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    /**
     * Retrieves a single company from the database by its ID.
     * @param companyId The ID of the company to retrieve.
     * @return The Company object, or null if no company is found with the given ID.
     */
    public Company getCompanyById(int companyId) {
        String sql = "SELECT * FROM companies WHERE company_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, companyId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getInt("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                return company;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}