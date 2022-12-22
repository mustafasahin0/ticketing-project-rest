package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    static UserDTO userDTO;
    static ProjectDTO projectDTO;

    static String token;

    @BeforeAll
    static void setup() {

        token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJlRWpLTEQxSUFfMzNXdXhjYllyb29xVjl4Q0RUSkpRSFJBX1JfVDJRcThNIn0.eyJleHAiOjE2NzE3Mzk4NjgsImlhdCI6MTY3MTczOTU2OCwianRpIjoiZGUyODVkOTUtYzUwZS00Zjc2LTk4MmYtNWE0NjhkZGM2MjU5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2N5ZGVvLWRldiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJmZDg0MjljMS00YTgwLTQ3MDAtOTdlZi04YWZkOTU4M2ZmNGUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ0aWNrZXRpbmctYXBwIiwic2Vzc2lvbl9zdGF0ZSI6ImEyZjFkYWZhLTI0ODUtNGU2Zi1hZWRiLTRmYzJiM2VmOGY1ZiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgxIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtY3lkZW8tZGV2IiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJ0aWNrZXRpbmctYXBwIjp7InJvbGVzIjpbIkFkbWluIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIiwic2lkIjoiYTJmMWRhZmEtMjQ4NS00ZTZmLWFlZGItNGZjMmIzZWY4ZjVmIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsInByZWZlcnJlZF91c2VybmFtZSI6Im96enkifQ.hLd3DK6oaX0KgwA5fEHiFqRqpIYcToeV0Gd_zQx-Ggj4NSDoV7ly7sdBdaEw-3_UZQsetnnE7kH9oEoN9El8oVjPnTq2rNR31NdTmG6nonns97WsTvennbQI5PbHUwgISiDODxKX9zqNtA1Tet6pxqptKxPHTcyxF-CtbTd2nfFH9_m9AqiXzMail6S3mL92DHfjxRn6pMcOg5kStAxqXBNyANn__LDsVBSMXWMifUuHOBu2Ck2KzdfcAHot9QhV3jjqYvZAB8APoPKNnAIopYI-BiwMDXo4tXWocKVbeUsNyr677XpfgxjI39EsaPYJ58iuZb-s9EkQ07UArqmYWA";

        userDTO = UserDTO.builder()
                .id(2L)
                .firstName("ozzy")
                .lastName("ozzy")
                .userName("ozzy")
                .passWord("admin")
                .confirmPassWord("admin")
                .role(new RoleDTO(2L, "Manager"))
                .gender(Gender.MALE)
                .build();
        projectDTO = ProjectDTO.builder()
                .projectCode("Api1")
                .projectName("Api-ozzy")
                .assignedManager(userDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .projectDetail("Api Test")
                .projectStatus(Status.OPEN)
                .build();
    }

    @Test
    public void givenNoToken_whenGetRequest() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/project"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenToken_whenGetRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/project")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}