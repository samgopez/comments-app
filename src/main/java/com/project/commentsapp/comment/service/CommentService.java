package com.project.commentsapp.comment.service;

import com.project.commentsapp.comment.dto.CommentDto;
import com.project.commentsapp.comment.dto.CommentRequestDto;

public interface CommentService {

    CommentDto updateComment(Long id, CommentRequestDto commentRequestDto);

    void deleteComment(Long id);
}
