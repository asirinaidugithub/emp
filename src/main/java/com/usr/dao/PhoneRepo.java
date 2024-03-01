package com.usr.dao;

import com.usr.entity.User;
import com.usr.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Long> {
    void deleteByUser(User user);
}
