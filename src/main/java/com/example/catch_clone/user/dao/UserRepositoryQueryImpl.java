package com.example.catch_clone.user.dao;

import static com.example.catch_clone.review.entity.QReview.review;
import static com.example.catch_clone.review.entity.QReviewLike.reviewLike;

import com.example.catch_clone.review.dto.ReviewResponseDto;
import com.example.catch_clone.user.dto.UserDto;
import com.example.catch_clone.user.entity.User;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryQueryImpl implements UserRepositoryQuery{
    private final JPAQueryFactory jpaQueryFactory;


    //Q 클래스가 필요하다는데 build.gradle에서 주석된거 지워도 되는건가요?
    @Override
    public void saveAccount(String username, String password) {
        /*
        jpaQueryFactory.insert(user)
            .set(user.username, username)
            .set(user.password, password)
            .execute();

         */
    }

    @Override
    public Optional<UserDto> findUserId(Long id) {
        return null;
    }
}
