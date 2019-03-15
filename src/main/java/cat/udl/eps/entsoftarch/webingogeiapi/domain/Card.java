package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Game;
import cat.udl.eps.entsoftarch.webingogeiapi.domain.Player;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Card extends UriEntity<Integer> {
    private int rows = 3;
    private int cols = 9;

    public Card() { }

    /**
     * Identifier of card needs to be unique, otherwise it will generate conflicts.
     */
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Game game;

    /**
     * List of numbers associated to a card.
     */
    @NonNull
    private int[][] numbers = new int[rows][cols];

    /**
     * Generates a card with 9 columns and 3 rows and random numbers.
     */
    public void generateCard(){
        Random r = new Random();
        ArrayList<Integer> generatedNumbers = new ArrayList<>();

        // Generate a list with random numbers.
        for (int i = 0; i < cols; i++){
            int temp = r.nextInt(10) + 10*i;
            generatedNumbers.add(temp);

            for (int j = 1; j < rows; j++){
                while(generatedNumbers.contains(temp))
                    temp = r.nextInt(10) + 10*i;
                generatedNumbers.add(temp);
            }
        }

        Collections.sort(generatedNumbers);

        // Assign the sorted list to the array.
        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                numbers[j][i] = generatedNumbers.get(j + i*rows);
    }
}
