package guru.springframework;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest
{
  Category category;

  @Before
  public void setUp()
  {
    category = new Category();
  }

  @Test
  public void getId()
  {
    category.setId(4L);

    assertEquals((Long) 4L, category.getId());
  }

  @Test
  public void getDescription()
  {
  }

  @Test
  public void getRecipes()
  {
  }
}