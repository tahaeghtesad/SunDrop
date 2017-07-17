package ir.arcinc.sundrop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by tahae on 7/17/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent")
    @Cascade(value = CascadeType.ALL)
    private List<Directory> subdirs;

    @ManyToOne
    @Cascade(value = CascadeType.ALL)
    private Directory parent;

    @OneToMany(mappedBy = "parent")
    @Cascade(value = CascadeType.ALL)
    private List<File> files;

    @ManyToOne
    private User owner;
}
