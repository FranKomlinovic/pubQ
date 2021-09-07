package hr.brocom.generic.abstraction.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@ToString
@MappedSuperclass
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings({"PMD.AbstractClassWithoutAbstractMethod", "PMD.AbstractClassWithoutAnyMethod"})
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Timestamp created;

    @UpdateTimestamp
    private Timestamp updated;

    private Boolean active = true;

}

