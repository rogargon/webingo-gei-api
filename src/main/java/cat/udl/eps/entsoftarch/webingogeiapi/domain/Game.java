package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Game extends UriEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private GameStatus status;
    private ArrayList<Integer> numbers;
    private Double jackpot;
    private Double pricePerCard;
    private boolean bingo;
    private boolean line;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime finishedAt, startAt, createdAt;

    public void setNumbers(){
        numbers = new ArrayList<>();
        for(int i=0;i<=100; i++) {
            numbers.add(i);
        }
    }
}

