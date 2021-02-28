package com.training.universitydb.service;

import com.training.universitydb.controller.exception.DataNotFoundException;
import com.training.universitydb.model.CourseEntity;
import com.training.universitydb.model.dto.CourseDTORequest;
import com.training.universitydb.model.dto.CourseDTOResponse;
import com.training.universitydb.repository.CourseRepository;
import com.training.universitydb.service.mapper.CourseDTOMapper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @InjectMocks
    CourseService courseService;
    @Mock
    CourseRepository courseRepository;
    @Mock
    CourseDTOMapper courseDTOMapper;
    @Mock
    TeachingCourseService teachingCourseService;

    @BeforeEach
    public void setup(){
        courseRepository = mock(CourseRepository.class);
        courseDTOMapper = mock(CourseDTOMapper.class);
        teachingCourseService = mock(TeachingCourseService.class);

        courseService = new CourseService(courseRepository, courseDTOMapper, teachingCourseService);
    }

    @Nested
    @DisplayName("Testing getById() method")
    class GetByIdTest {
        @Test
        public void whenGetByIdCourse_returnCourse() {
            CourseEntity courseEntity = mock(CourseEntity.class);

            CourseDTOResponse courseDTOResponse = mock(CourseDTOResponse.class);

            when(courseRepository.findById(1l)).thenReturn(Optional.of(courseEntity));
            when(courseDTOMapper.entityToDto(courseEntity)).thenReturn(courseDTOResponse);

            CourseDTOResponse test = courseService.getById(1l);

            assertSame(courseDTOResponse, test);
            verify(courseRepository, times(1)).findById(anyLong());
        }

        @Test
        public void whenGetByIdWrongCourse_returnException() {
            when(courseRepository.findById(23l)).thenReturn(Optional.empty());

            assertThrows(DataNotFoundException.class, () -> courseService.getById(23l));
            verify(courseRepository, times(1)).findById(anyLong());
        }
    }

    @Nested
    @DisplayName("Testing getAll() method")
    class GetAllTest{
        @Test
        public void whenGetAll_returnAll() {
            CourseEntity courseEntity = mock(CourseEntity.class);
            CourseDTOResponse courseDTOResponse = mock(CourseDTOResponse.class);
            List<CourseEntity> courseEntities = new ArrayList<>();
            courseEntities.add(courseEntity);
            List<CourseDTOResponse> courseDTOResponses = new ArrayList<>();
            courseDTOResponses.add(courseDTOResponse);

            when(courseRepository.findAll()).thenReturn(courseEntities);
            when(courseDTOMapper.entityToDto(courseEntity)).thenReturn(courseDTOResponse);

            List<CourseDTOResponse> test = courseService.getAll();

            assertArrayEquals(courseDTOResponses.toArray(), test.toArray());
            verify(courseRepository, times(1)).findAll();
        }
    }

    @Nested
    @DisplayName("Testing removeById() method")
    class RemoveByIdTest {
        @Test
        public void whenRemoveByIdCourse_callDelete() {
            CourseEntity courseEntity = mock(CourseEntity.class);

            when(courseRepository.findById(1l)).thenReturn(Optional.of(courseEntity));

            courseService.removeById(1l);

            verify(courseRepository, times(1)).findById(anyLong());
            verify(courseRepository, times(1)).delete(any(CourseEntity.class));
        }

        @Test
        public void whenRemoveByIdWrongCourse_returnException() {
            when(courseRepository.findById(23l)).thenReturn(Optional.empty());

            assertThrows(DataNotFoundException.class, () -> courseService.removeById(23l));
            verify(courseRepository, times(1)).findById(anyLong());
            verify(courseRepository, never()).delete(any());
        }
    }

    @Nested
    @DisplayName("Testing create() method")
    class CreateTest {
        @Test
        public void whenCreateCourse_callSave() {
            CourseEntity courseEntity = mock(CourseEntity.class);

            CourseDTORequest courseDTORequest = mock(CourseDTORequest.class);

            when(courseDTOMapper.dtoToEntity(courseDTORequest)).thenReturn(courseEntity);

            courseService.create(courseDTORequest);

            assertSame(courseDTORequest.getTitle(), courseEntity.getTitle());
            assertSame(courseDTORequest.getCredit(), courseEntity.getCredit());
            verify(courseRepository, times(1)).save(courseEntity);
        }
    }

    @Nested
    @DisplayName("Testing update() method")
    class UpdateTest {
        @Test
        public void whenUpdateCourse_callSave() {
            CourseEntity courseEntity = mock(CourseEntity.class);

            CourseDTORequest courseDTORequest = mock(CourseDTORequest.class);

            when(courseRepository.findById(1L)).thenReturn(Optional.of(courseEntity));

            courseService.update(courseDTORequest, 1L);

            assertSame(courseDTORequest.getTitle(), courseEntity.getTitle());
            assertSame(courseDTORequest.getCredit(), courseEntity.getCredit());
            verify(courseRepository, times(1)).save(courseEntity);
        }
    }
}
