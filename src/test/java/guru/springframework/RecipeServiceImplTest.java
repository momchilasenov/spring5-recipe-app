package guru.springframework;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.service.RecipeService;
import guru.springframework.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class RecipeServiceImplTest
{
  RecipeService         recipeService;
  RecipeCommandToRecipe recipeCommandToRecipe;
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  RecipeRepository recipeRepository;

  @Before
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void wires()
  {
    assertNotNull(recipeService);
    assertNotNull(recipeRepository);
  }

  @Test
  public void getRecipes()
  {
    when(recipeRepository.findAll())
        .thenReturn(new HashSet<>(Collections.singleton(new Recipe())));

    Set<Recipe> recipes = recipeService.getRecipes();
    assertEquals(recipes.size(), 1);
  }
}
