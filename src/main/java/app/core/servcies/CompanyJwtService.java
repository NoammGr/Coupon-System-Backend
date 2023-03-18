package app.core.servcies;

import app.core.auth.CompanyJwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.Company;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyJwtService {
    @Autowired
    CompanyJwtUtil companyJwtUtil;
    @Autowired
    CompanyRepository companyRepository;

    public String register(Company company) throws Exception {
        if (!companyRepository.existsByEmail(company.getEmail())) {
            this.companyRepository.save(company);
            return this.companyJwtUtil.generateToken(company);
        } else {
            throw new Exception("Register failed - Email already in use - " + company.getEmail());
        }
    }

    public String login(UserCredentials userCredentials) throws Exception {
        Company company = companyRepository.findByEmail(userCredentials.getEmail());
        if (company != null) {
            if (userCredentials.getPassword().equals(company.getPassword())) {
                return companyJwtUtil.generateToken(company);
            } else {
                throw new Exception("Wrong email or password ");
            }
        }
        throw new CouponSystemException("Company email not found !");
    }
}
