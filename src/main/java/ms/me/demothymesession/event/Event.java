package ms.me.demothymesession.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class Event {
    private Long id;
    @NotEmpty(groups = FirstValidGroup.class)
    private String name;
    @Positive(groups = SecondValidGroup.class)
    private long limit;

    interface FirstValidGroup{}
    interface SecondValidGroup{}
}
