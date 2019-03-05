package cat.udl.eps.entsoftarch.webingogeiapi.domain;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Entity
public class Card {
    private int rows = 3;
    private int cols = 9;

    /**
     * Identifier of card needs to be unique, otherwise it will generate conflicts.
     */
    @Id
    private int id;


    /**
     * List of numbers associated to a card.
     */
    @NonNull
    @NotBlank
    private int[][] numbers = new int[rows][cols];

    public int getId() {
        return id;
    }
    

}
