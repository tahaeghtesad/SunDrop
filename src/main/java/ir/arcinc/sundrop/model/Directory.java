package ir.arcinc.sundrop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    @JsonIgnore
    private List<Directory> subdirs;

    @ManyToOne
    @Cascade(value = CascadeType.ALL)
    @JsonIgnore
    private Directory parent;

    @OneToMany(mappedBy = "parent")
    @Cascade(value = CascadeType.ALL)
    @JsonIgnore
    private List<File> files;

    @ManyToOne
    @JsonIgnore
    private User owner;
}
