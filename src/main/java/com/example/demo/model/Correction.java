package com.example.demo.model;

        import lombok.Data;
        import javax.persistence.*;
        import java.time.LocalDate;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Data
@Entity
@Table(name="correction")
public class Correction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "correction_gen")
    @SequenceGenerator(name = "correction_gen",sequenceName = "correction_seq",allocationSize = 1)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Courbure")
    private Integer courbure;

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User author;
    @Column(name = "Date")
    private LocalDate createDate;
}
