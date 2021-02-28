package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/*
این مدل برای نشان دادن «ارایه شدن درس» به کار می ره.
مثلا : کلاس درس ریاضی یک با استاد کریمی در ترم پاییز ۹۹
*/

@Entity
@Table(name = "teaching_course")
@Getter
@Setter
@NoArgsConstructor
public class TeachingCourseEntity {
    @Id
    @GeneratedValue
    private Long id;

    //ارتباط کلاسِ ارایه شده و ترم آن
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;

    //ارتباط کلاسِ ارایه شده و موضوع درس آن
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    //ارتباط ارایه دادن
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @OneToOne(mappedBy = "teachingCourseEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TakingCourseEntity takingCourseEntity;
}
