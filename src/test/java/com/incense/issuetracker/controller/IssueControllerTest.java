package com.incense.issuetracker.controller;

import com.incense.issuetracker.common.CommonString;
import com.incense.issuetracker.dto.issue.response.IssueListResponseDto;
import com.incense.issuetracker.dto.label.response.LabelResponseDto;
import com.incense.issuetracker.security.CustomAccessDeniedHandler;
import com.incense.issuetracker.security.CustomAuthenticationEntryPoint;
import com.incense.issuetracker.security.oauth.CustomOauth2UserService;
import com.incense.issuetracker.security.oauth.OAuth2LoginSuccessHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssueController.class)
@AutoConfigureRestDocs
class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueController issueController;

    @MockBean
    private CustomOauth2UserService oAuth2UserService;

    @MockBean
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @MockBean
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @MockBean
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Test
    @WithMockUser
    void 이슈리스트_가져오기() throws Exception {
        //given
        List<LabelResponseDto> labels1 = Arrays.asList(
                LabelResponseDto.builder().id(1L).title("라벨1").description("설명1").backgroundColor("#123456").textColor("#000000").build(),
                LabelResponseDto.builder().id(2L).title("라벨2").description("설명2").backgroundColor("#654321").textColor("#111111").build()
        );

        List<LabelResponseDto> labels2 = Arrays.asList(
                LabelResponseDto.builder().id(3L).title("라벨3").description("설명3").backgroundColor("#453212").textColor("#000000").build()
        );

        List<IssueListResponseDto> issues = Arrays.asList(
                IssueListResponseDto.builder().id(1L).title("제목1").isOpened("y").date("2020.10.11").writerName("작성자1").writerImage("이미지1").milestoneTitle("스프린트1").commentCount(0).labels(labels1).build(),
                IssueListResponseDto.builder().id(2L).title("제목2").isOpened("n").date("2020.10.07").writerName("작성자2").writerImage("이미지2").milestoneTitle("스프린트3").commentCount(3).labels(labels2).build()
        );

        //when
        when(issueController.list()).thenReturn(ResponseEntity.ok(issues));
        ResultActions resultActions = mockMvc.perform(get("/issues"));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        preprocessRequest(modifyUris().scheme(CommonString.SCHEMA).host(CommonString.HOST)
                                , prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("이슈 id").type(Long.class),
                                fieldWithPath("[].title").description("제목").type(String.class),
                                fieldWithPath("[].isOpened").description("오픈 여부").type(String.class),
                                fieldWithPath("[].date").description("작성일").type(String.class),
                                fieldWithPath("[].writerName").description("작성자 이름").type(String.class),
                                fieldWithPath("[].writerImage").description("작성자 프로필 이미지").type(String.class),
                                fieldWithPath("[].milestoneTitle").description("마일스톤 제목").type(String.class),
                                fieldWithPath("[].milestoneTitle").description("마일스톤 제목").type(String.class),
                                fieldWithPath("[].milestoneTitle").description("마일스톤 제목").type(String.class),
                                fieldWithPath("[].commentCount").description("댓글 개수"),
                                fieldWithPath("[].labels").description("라벨 목록"),
                                fieldWithPath("[].labels[].id").description("라벨 id").type(Long.class),
                                fieldWithPath("[].labels[].title").description("라벨 제목").type(String.class),
                                fieldWithPath("[].labels[].description").description("라벨 설명").type(String.class),
                                fieldWithPath("[].labels[].backgroundColor").description("라벨 배경색").type(String.class),
                                fieldWithPath("[].labels[].textColor").description("라벨 글자색").type(String.class)
                        )
                ));
    }


}
