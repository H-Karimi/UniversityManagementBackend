package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نگه داری موجودیت «استاد» به کار می ره.
*/

@Entity
@Table(name = "professor", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_sequence")
    @SequenceGenerator(name="professor_sequence", initialValue=1, allocationSize=100)
    private Long id;

    private String name;
    private Timestamp birthDate;

    //ارتباط ارایه دادن
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private Set<TeachingCourseEntity> teachingCourseEntitySet = new HashSet<>();

    //ارتباط هیئت علمی
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "faculty")
    private FacultyEntity faculty;

    public ProfessorEntity(String name, Timestamp birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfessorEntity that = (ProfessorEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
