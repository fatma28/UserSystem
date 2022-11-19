package com.mycompany.user.system.repository;

import com.mycompany.user.system.dto.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer> {
    UserInfo findByCivilId(int civilId);
}
