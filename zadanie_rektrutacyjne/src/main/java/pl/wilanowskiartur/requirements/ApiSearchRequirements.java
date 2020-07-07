package pl.wilanowskiartur.requirements;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class ApiSearchRequirements {
    private int min;
    private int max;
}
