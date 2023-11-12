package com.example.catch_clone.annotation;

import com.example.catch_clone.util.enums.UserRoleEnum;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithCustomMockUser {

  String username() default "username1";

  String password() default "Password1!";
  UserRoleEnum role() default UserRoleEnum.CUSTOMER;

}
