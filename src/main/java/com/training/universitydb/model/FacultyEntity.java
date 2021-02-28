package com.training.universitydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
این مدل برای نگه داری موجودیت «دانشکده» به کار می ره.
مثلا : دانشکده زمین شناسی
*/

@Entity
@Table(name = "faculty")
@Getter
@Setter
@NoArgsConstructor
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_sequence")
    @SequenceGenerator(name="faculty_sequence", initialValue=1, allocationSize=100)
    private Long id;

    //ارتباط درس خواندن در(عضو بودن)
    @OneToMany(mappedBy = "faculty", targetEntity = StudentEntity.class, fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Set<StudentEntity> students = new HashSet<>();

    //ارتباط هیئت علمی
    @OneToMany(mappedBy = "faculty", targetEntity = ProfessorEntity.class, fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private Set<ProfessorEntity> professors = new HashSet<>();

    //ارتباط ریاست
    @OneToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private ProfessorEntity headProfessor;

    private String title;

    public FacultyEntity(String title, ProfessorEntity headProfessor) {
        this.title = title;
        this.headProfessor = headProfessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacultyEntity that = (FacultyEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
