package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByEmailAndPassword(String email, String password);

    Boolean existsByName(String name);

    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Company findByEmail(String email);

}
