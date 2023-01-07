package com.ourgame.ourgameserver.ws.model.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
interface UserRepository extends CrudRepository<UserJpaEntity, String> {
}
