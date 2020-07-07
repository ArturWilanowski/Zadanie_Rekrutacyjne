package pl.wilanowskiartur.requirements;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class RandomSearchRequirements {
    private int min;
    private int max;
}
