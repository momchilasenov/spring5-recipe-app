package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Ingredient
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String     description;
  private BigDecimal amount;

  @ManyToOne //no cascade, if ingredient is deleted, Recipe should not be deleted
  private Recipe recipe;

  @OneToOne(fetch = FetchType.EAGER)
  private UnitOfMeasure uom;

  public Ingredient()
  {
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe)
  {
    this.description = description;
    this.amount = amount;
    this.uom = uom;
    this.recipe = recipe;
  }
}
