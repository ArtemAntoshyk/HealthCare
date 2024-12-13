package devtitans.antoshchuk.healthcare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "result_of_examination")
public class ResultOfExamination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "referral_id")
    private ReferralForExamination referral;

    @Column(name = "result_description", length = Integer.MAX_VALUE)
    private String resultDescription;

    @Size(max = 255)
    @Column(name = "value")
    private String value;

    @Column(name = "more_information", length = Integer.MAX_VALUE)
    private String moreInformation;

    @Size(max = 255)
    @Column(name = "files")
    private String files;

}