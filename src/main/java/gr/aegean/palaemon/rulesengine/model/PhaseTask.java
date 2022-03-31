package gr.aegean.palaemon.rulesengine.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhaseTask {
    private String phase;
    private String task;
    private MessageObject messageObject;
}
