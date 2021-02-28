package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نشان دادن «کلاس ارایه شده» یا «کلاس برای انتخاب»  به کار می ره.
*/

@Entity
@Table(name = "taking_course")
@Getter
@Setter
@NoArgsConstructor
public class TakingCourseEntity {
    @Id
    @GeneratedValue
    private Long id;

    //ارتباط
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teaching_course_id", referencedColumnName = "id")
    private TeachingCourseEntity teachingCourseEntity;

    //ارتباط شرکت کردن در کلاس
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Set<StudentEntity> students = new HashSet<>();

}
