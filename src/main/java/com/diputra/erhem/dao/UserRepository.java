package com.diputra.erhem.dao;

import com.diputra.erhem.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    
}
