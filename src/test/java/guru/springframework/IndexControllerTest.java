package guru.springframework;

import guru.springframework.controllers.IndexController;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IndexControllerTest
{
  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  IndexController indexController;

  @Before
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    indexController = new IndexController(recipeService);
  }

  @Test
  public void getIndexPage()
  {
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe());
    recipes.add(new Recipe());

    when(recipeService.getRecipes()).thenReturn(recipes);
    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
    String viewName = indexController.getIndexPage(model);

    assertEquals("index", viewName);
    verify(recipeService, times(1)).getRecipes();

    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());
  }
}
