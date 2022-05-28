package guru.springframework;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureIT
{
  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @Test
  @DirtiesContext //reload the spring context
  public void findByDescription()
  {
    Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findByDescription("Teaspoon");
    assertEquals("Teaspoon", unit.get().getDescription());
  }

  @Test
  public void findByDescription2() //runs faster - context already loaded
  {
    Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findByDescription("Cup");
    assertEquals("Cup", unit.get().getDescription());
  }
}
