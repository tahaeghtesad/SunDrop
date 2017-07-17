package ir.arcinc.sundrop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by tahae on 7/17/2017.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String extension;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Directory parent;

    private String path;
}
