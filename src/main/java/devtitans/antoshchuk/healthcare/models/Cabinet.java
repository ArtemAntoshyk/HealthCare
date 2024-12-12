package devtitans.antoshchuk.healthcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "cabinet")
public class Cabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "cabinet_number", nullable = false)
    private Long cabinetNumber;

    @NotNull
    @Column(name = "floor", nullable = false)
    private Long floor;

}