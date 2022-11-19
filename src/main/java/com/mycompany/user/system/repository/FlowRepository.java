package com.mycompany.user.system.repository;

import com.mycompany.user.system.dto.UserRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends CrudRepository<UserRequest, Integer> {


}
