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
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = -6336226054194805408L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "status", length = 64)
    private String status;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

}