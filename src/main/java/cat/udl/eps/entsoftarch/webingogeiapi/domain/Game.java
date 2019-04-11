package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private double pricePerCard;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime finishedAt, createdAt;

    @Override
    public void setStartAt(ZonedDateTime now) {

    }

    @Override
    public Integer getId() {
        return null;
    }
}