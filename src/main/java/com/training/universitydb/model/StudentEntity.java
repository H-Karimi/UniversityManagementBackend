package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نگه داری موجودیت «دانشجو» به کار می ره.
*/

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @SequenceGenerator(name="student_sequence", initialValue=1, allocationSize=100)
    private Long id;

    private String name;
    private Timestamp birthDate;
    private int year;

    //ارتباط درس خواندن در(عضو بودن)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty")
    private FacultyEntity faculty;

    //ارتباط شرکت کردن در کلاس
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinTable(
            name = "courses_taken_by_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<TakingCourseEntity> courses = new HashSet<>();

    public StudentEntity(String name, Timestamp birthDate, int year) {
        this.name = name;
        this.birthDate = birthDate;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
