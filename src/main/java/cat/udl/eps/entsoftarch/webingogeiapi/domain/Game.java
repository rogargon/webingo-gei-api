package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Game extends UriEntity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;
    private int status;
    private float jackpot;
    private boolean bingo;
    private boolean line;
    @NotNull
    @DecimalMin("0.1")
    private double pricePerCard;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime finishedAt, createdAt;
}