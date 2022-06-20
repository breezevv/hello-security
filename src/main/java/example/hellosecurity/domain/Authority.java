package example.hellosecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements Serializable {
    private static final long serialVersionUID = 6390722976022707775L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "url", length = 1000)
    private String url;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "permission", length = 50)
    private String permission;

    @Column(name = "method", length = 50)
    private String method;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

}