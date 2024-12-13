package devtitans.antoshchuk.healthcare.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

    @Column(name = "complaint", length = Integer.MAX_VALUE)
    private String complaint;

    @Column(name = "doctors_appointment", length = Integer.MAX_VALUE)
    private String doctorsAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_of_examination_id")
    private ResultOfExamination resultOfExamination;

    @Column(name = "additionally", length = Integer.MAX_VALUE)
    private String additionally;

}