package ir.arcinc.sundrop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private Directory parent;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private User owner;

    @JsonIgnore
    private String path;
}
