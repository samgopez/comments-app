package com.project.commentsapp.common.security.service;

import com.project.commentsapp.common.security.dto.AuthUser;

public interface SecurityService {

    AuthUser getCurrentUser();
}
