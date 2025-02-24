package com.project.commentsapp.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void testUserCanUpdateOwnComment() throws Exception {
        String commentRequest = """
                {
                    "content": "First comment updated"
                }
                """;
        mockMvc.perform(put("/api/v1/comments/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("First comment updated"))
                .andExpect(jsonPath("$.author").value("user1"));
    }

    @Test
    @WithMockUser(username = "user2", roles = "USER")
    void testUserCannotUpdateOthersComment() throws Exception {
        String commentRequest = """
                {
                    "content": "First comment updated"
                }
                """;
        mockMvc.perform(put("/api/v1/comments/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentRequest))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminCanUpdateAnyComment() throws Exception {
        String commentRequest = """
                {
                    "content": "First comment updated"
                }
                """;
        mockMvc.perform(put("/api/v1/comments/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("First comment updated"))
                .andExpect(jsonPath("$.author").value("user1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminUpdateCommentNotFound() throws Exception {
        String commentRequest = """
                {
                    "content": "First comment updated"
                }
                """;
        mockMvc.perform(put("/api/v1/comments/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentRequest))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Comment not found with id: 9999"));
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void testUserCanDeleteOwnComment() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/{id}", 2))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "user2", roles = "USER")
    void testUserCannotDeleteOthersComment() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/{id}", 3))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminCanDeleteAnyComment() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/{id}", 4))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testAdminDeleteCommentNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/{id}", 9999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Comment not found with id: 9999"));
    }
}
