package com.example.catch_clone.user.dao;


import static com.example.catch_clone.review.entity.QReview.review;
import static com.example.catch_clone.user.entity.QUser.user;
import com.example.catch_clone.user.dto.UserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return jpaQueryFactory.from(user).where(user.username.eq(username)).select(user.username)
            .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
    }
}
