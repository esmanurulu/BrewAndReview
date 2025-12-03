package com.brewandreview.repository;

import com.brewandreview.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);

    Employee findByCitizenId(String citizenId);
}