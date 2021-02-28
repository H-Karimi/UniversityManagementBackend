package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نگه داری موجودیت «ترم» به کار می ره.
مثلا : ترم پاییز ۹۹
*/

@Entity
@Table(name = "semester")
@Getter
@Setter
@NoArgsConstructor
public class SemesterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semester_sequence")
    @SequenceGenerator(name="semester_sequence", initialValue=1, allocationSize=100)
    private Long id;

    private Timestamp startDate;
    private Timestamp endDate;

    //ارتباط کلاسِ ارایه شده و ترم
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private Set<TeachingCourseEntity> teachingCourseEntitySet = new HashSet<>();

    public SemesterEntity(Timestamp startDate, Timestamp endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SemesterEntity that = (SemesterEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
