package com.example.catch_clone.user.dao;


import static com.example.catch_clone.user.entity.QUser.user;
import com.example.catch_clone.user.dto.UserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryQueryImpl implements UserRepositoryQuery{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void saveAccount(String username, String password) {
        jpaQueryFactory.insert(user)
            .set(user.username, username)
            .set(user.password, password)
            .execute();
    }
}
