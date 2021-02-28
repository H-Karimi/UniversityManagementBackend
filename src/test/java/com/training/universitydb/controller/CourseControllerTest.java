package com.training.universitydb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.model.dto.CourseDTORequest;
import com.training.universitydb.model.dto.CourseDTOResponse;
import com.training.universitydb.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(new CourseController(courseService)).build();
    }

    @Nested
    @DisplayName("Testing getAll() method")
    class GetAllTest{
        @Test
        public void whenGetAll_returnResponseEntity() throws Exception {
            CourseDTOResponse courseDTOResponse1 = new CourseDTOResponse();
            courseDTOResponse1.setId(1l);
            courseDTOResponse1.setTitle("Math");
            courseDTOResponse1.setCredit(3);

            CourseDTOResponse courseDTOResponse2 = new CourseDTOResponse();
            courseDTOResponse2.setId(2l);
            courseDTOResponse2.setTitle("Lab");
            courseDTOResponse2.setCredit(2);

            List<CourseDTOResponse> courseDTOResponseList = new ArrayList<>();
            courseDTOResponseList.add(courseDTOResponse1);
            courseDTOResponseList.add(courseDTOResponse2);

            when(courseService.getAll()).thenReturn(courseDTOResponseList);

            mockMvc.perform(get("/course"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].title", is("Math")))
                    .andExpect(jsonPath("$[0].credit", is(3)))
                    .andExpect(jsonPath("$[1].id", is(2)))
                    .andExpect(jsonPath("$[1].title", is("Lab")))
                    .andExpect(jsonPath("$[1].credit", is(2)));

            verify(courseService, times(1)).getAll();
        }
    }

    @Nested
    @DisplayName("Testing getById() method")
    class GetByIdTest{
        @Test
        public void whenGetById_returnResponseEntity() throws Exception {
            CourseDTOResponse courseDTOResponse = new CourseDTOResponse();
            courseDTOResponse.setId(1l);
            courseDTOResponse.setTitle("Math");
            courseDTOResponse.setCredit(3);

            when(courseService.getById(1l)).thenReturn(courseDTOResponse);

            mockMvc.perform(get("/course/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("Math")))
                    .andExpect(jsonPath("$.credit", is(3)));

            verify(courseService, times(1)).getById(anyLong());
        }

        @Test
        public void whenGetByWrongId_returnException() throws Exception {
            when(courseService.getById(1l)).thenThrow(new DataNotFoundException());

            mockMvc.perform(get("/course/1"))
                    .andExpect(status().isNotFound());

            verify(courseService, times(1)).getById(anyLong());
        }
    }

    @Nested
    @DisplayName("Testing removeById() method")
    class RemoveByIdTest{
        @Test
        public void whenRemoveById_returnOk() throws Exception {
            mockMvc.perform(delete("/course/1"))
                    .andExpect(status().isOk());

            verify(courseService, times(1)).removeById(anyLong());
        }

        @Test
        public void whenRemoveByWrongId_returnException() throws Exception {
            when(courseService.removeById(1L)).thenThrow(new DataNotFoundException());

            mockMvc.perform(delete("/course/1"))
                    .andExpect(status().isNotFound());

            verify(courseService, times(1)).removeById(anyLong());
        }
    }

    @Nested
    @DisplayName("Testing create() method")
    class CreateTest{
        @Test
        public void whenCreate_returnOk() throws Exception {
            CourseDTORequest courseDTORequest = new CourseDTORequest();
            courseDTORequest.setTitle("Math");
            courseDTORequest.setCredit(3);

            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(courseDTORequest);

            mockMvc.perform(
                    post("/course")
                            .content(jsonRequest)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(courseService, times(1)).create(any());
        }
    }

    @Nested
    @DisplayName("Testing update() method")
    class UpdateTest{
        @Test
        public void whenUpdate_returnOk() throws Exception {
            CourseDTORequest courseDTORequest = new CourseDTORequest();
            courseDTORequest.setTitle("Math");
            courseDTORequest.setCredit(3);

            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(courseDTORequest);

            mockMvc.perform(
                    put("/course/1")
                            .content(jsonRequest)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            verify(courseService, times(1)).update(any(), anyLong());
        }

        @Test
        public void whenUpdateWrongId_returnException() throws Exception {
            CourseDTORequest courseDTORequest = new CourseDTORequest();
            courseDTORequest.setTitle("Math");
            courseDTORequest.setCredit(3);

            ObjectMapper mapper = new ObjectMapper();
            String jsonRequest = mapper.writeValueAsString(courseDTORequest);

            when(courseService.update(any(), eq(1L))).thenThrow(new DataNotFoundException());

            mockMvc.perform(
                    put("/course/1")
                            .content(jsonRequest)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

            verify(courseService, times(1)).update(any(), anyLong());
        }
    }
}
