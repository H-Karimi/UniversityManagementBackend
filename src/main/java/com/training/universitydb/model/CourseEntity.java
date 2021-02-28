package com.training.universitydb.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نگه داری موجودیت «درس» به کار می ره.
مثلا : ریاضی ۱
*/

@Entity
@Table(name = "course", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}), catalog = "university")
@Getter
@Setter
@NoArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @SequenceGenerator(name="course_sequence", initialValue=1, allocationSize=1)
    private Long id;

    @Column(name = "title", length = 150)
    private String title;
    @Column(precision = 0, scale = 1, unique = false)
    private int credit;

    //ارتباط کلاسِ ارایه شده و موضوع درس آن
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<TeachingCourseEntity> teachingCourseEntitySet = new HashSet<>();

    public CourseEntity(String title, int credit) {
        this.title = title;
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
