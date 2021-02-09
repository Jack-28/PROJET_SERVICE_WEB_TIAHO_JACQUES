package com.example.demo.dto;
import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrectionDTO {
    private Integer courbure;
    private Integer authorId;
    private LocalDate createDate;
}
