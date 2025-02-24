package com.project.commentsapp.comment.service.impl;

import com.project.commentsapp.comment.dto.CommentDto;
import com.project.commentsapp.comment.dto.CommentRequestDto;
import com.project.commentsapp.comment.entity.Comment;
import com.project.commentsapp.comment.repository.CommentRepository;
import com.project.commentsapp.comment.service.CommentService;
import com.project.commentsapp.common.exception.NotFoundException;
import com.project.commentsapp.common.exception.ForbiddenException;
import com.project.commentsapp.common.security.dto.AuthUser;
import com.project.commentsapp.common.security.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final SecurityService securityService;

    @Override
    public CommentDto updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = this.getComment(id);

        this.checkUserAuthorityForComment(comment);

        comment.setContent(commentRequestDto.getContent());
        this.commentRepository.save(comment);

        return CommentDto.of(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = this.getComment(id);

        this.checkUserAuthorityForComment(comment);

        this.commentRepository.delete(comment);
    }

    private Comment getComment(Long id) {
        return this.commentRepository.findCommentFetchAuthorById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id: " + id));
    }

    private void checkUserAuthorityForComment(Comment comment) {
        AuthUser authUser = this.securityService.getCurrentUser();
        if (!authUser.isAdmin() && !comment.getAuthor().getUsername().equals(authUser.getUsername())) {
            throw new ForbiddenException("You do not have permission to modify this comment");
        }
    }
}
