package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
  private final RecipeRepository        recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final CategoryRepository      categoryRepository;

  public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository)
  {
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
  {
    recipeRepository.saveAll(getRecipes());
  }

  private List<Recipe> getRecipes()
  {
    List<Recipe> recipes = new ArrayList<>(2);

    UnitOfMeasure tableSpoonUom = unitOfMeasureRepository
        .findByDescription("Tablespoon")
        .orElseThrow(RuntimeException::new);

    UnitOfMeasure teaSpoonUom = unitOfMeasureRepository
        .findByDescription("Teaspoon")
        .orElseThrow(RuntimeException::new);

    UnitOfMeasure dashUom = unitOfMeasureRepository
        .findByDescription("Dash")
        .orElseThrow(RuntimeException::new);

    UnitOfMeasure pintUom = unitOfMeasureRepository
        .findByDescription("Pint")
        .orElseThrow(RuntimeException::new);

    UnitOfMeasure cupsUom = unitOfMeasureRepository
        .findByDescription("Cup")
        .orElseThrow(RuntimeException::new);

    Category americanCategory = categoryRepository
        .findByDescription("American")
        .orElseThrow(RuntimeException::new);

    Category mexicanCategory = categoryRepository
        .findByDescription("Mexican")
        .orElseThrow(RuntimeException::new);

    Recipe guacRecipe = new Recipe();
    guacRecipe.setDescription("Perfect guac");
    guacRecipe.setPrepTime(10);
    guacRecipe.setCookTime(10);
    guacRecipe.setDifficulty(Difficulty.EASY);
    guacRecipe.setDirections("Guac Description of the recipe");

    Notes guacNotes = new Notes();
    guacNotes.setRecipeNotes("Guac Recipe Notes");

    guacRecipe.setNotes(guacNotes);

    guacRecipe.getIngredients().add(new Ingredient("avocados", new BigDecimal(2), pintUom, guacRecipe));
    guacRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(3), teaSpoonUom, guacRecipe));
    guacRecipe.getIngredients().add(new Ingredient("chicken", new BigDecimal(4), cupsUom, guacRecipe));
    guacRecipe.getIngredients().add(new Ingredient("tomato", new BigDecimal(9), dashUom, guacRecipe));
    guacRecipe.getIngredients().add(new Ingredient("avocados", new BigDecimal(2), pintUom, guacRecipe));

    guacRecipe.getCategories().add(americanCategory);
    guacRecipe.getCategories().add(mexicanCategory);

    recipes.add(guacRecipe);

    return recipes;
  }
}
