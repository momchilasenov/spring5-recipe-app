package guru.springframework.service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService
{
  private final RecipeRepository recipeRepository;
  private final IngredientToIngredientCommand ingredientToIngredientCommand;

  public IngredientServiceImpl(RecipeRepository recipeRepository,
                               IngredientToIngredientCommand ingredientToIngredientCommand)
  {
    this.recipeRepository = recipeRepository;
    this.ingredientToIngredientCommand = ingredientToIngredientCommand;
  }

  @Override
  public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId)
  {
    Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

    if (!recipeOptional.isPresent()){
      log.error("Recipe with id {} not found", recipeId);
      return new IngredientCommand();
    }

    Recipe recipe = recipeOptional.get();
    Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
        .filter(ingredient -> ingredient.getId().equals(ingredientId))
        .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
        .findFirst();

    if (!ingredientCommandOptional.isPresent()){
      log.error("Ingredient with id {} not found", ingredientId);
      return new IngredientCommand();
    }

    return ingredientCommandOptional.get();
  }
}
