package com.mark2.mglow.helper;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mark2.mglow.model.UserData;

public interface UserRepository extends CrudRepository<UserData,String>{
	
}
