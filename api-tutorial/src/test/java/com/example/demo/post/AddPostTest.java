package com.example.demo.post;

import com.example.demo.common.BaseControllerTest;
import com.example.demo.post.request.UpdatePostRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("포스트 저장 테스트")
public class AddPostTest extends BaseControllerTest {


    @Test
    @DisplayName("포스트 저장(성공)")
    void insertPostSuccess() throws Exception {
        // Given
        UpdatePostRequest updatePostRequest = new UpdatePostRequest("title", "body");
        // When
        ResultActions resultActions = this.mockMvc.perform(post("/posts/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        );
        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("body").value("body"))
                .andExpect(jsonPath("views").value(0))
                .andDo(document("insert-post",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("포스트 제목"),
                                fieldWithPath("body").type(JsonFieldType.STRING).description("포스트 본문")
                        ),
                        responseFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("포스트 제목"),
                                fieldWithPath("body").type(JsonFieldType.STRING).description("포스트 본문"),
                                fieldWithPath("views").type(JsonFieldType.NUMBER).description("조회수"))
                        )
                );
    }


    @Test
    @DisplayName("포스트 저장(실패) - 잘못된 body")
    void insertPostFailBecauseContentIsNull() throws Exception {
        // Given
        UpdatePostRequest updatePostRequest = new UpdatePostRequest("title", null);
        // When
        ResultActions resultActions = this.mockMvc.perform(post("/posts/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(updatePostRequest))
        );
        // Then
        resultActions.andExpect(status().isInternalServerError())
        ;
    }
}
