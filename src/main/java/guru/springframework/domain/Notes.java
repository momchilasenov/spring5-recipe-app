package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notes
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Recipe recipe; //no cascade - if note is deleted, the recipe is not deleted

  @Lob //jpa expects a CLOB in db
  private String recipeNotes;
}
