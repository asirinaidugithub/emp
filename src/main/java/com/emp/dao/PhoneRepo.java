package com.emp.dao;

import com.emp.entity.Employee;
import com.emp.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Long> {
    void deleteByEmployee(Employee employee);
}
