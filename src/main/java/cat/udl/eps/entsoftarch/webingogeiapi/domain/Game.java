package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private User gameRegister;
}

