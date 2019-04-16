package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Game extends UriEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private GameStatus status;
    private ArrayList<Integer> numbers;
    private double jackpot;
    private double pricePerCard;
    private boolean bingo;
    private boolean line;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime StartAt, finishedAt ;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Admin creator;


}

