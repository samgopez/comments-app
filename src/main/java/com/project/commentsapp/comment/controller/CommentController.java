package com.project.commentsapp.comment.controller;

import com.project.commentsapp.comment.dto.CommentDto;
import com.project.commentsapp.comment.dto.CommentRequestDto;
import com.project.commentsapp.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        CommentDto updatedComment = this.commentService.updateComment(id, commentRequestDto);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        this.commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
