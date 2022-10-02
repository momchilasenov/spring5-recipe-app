package guru.springframework.controllers;

import guru.springframework.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
  private final RecipeService recipeService ;

  public IndexController(RecipeService recipeService)
  {
    this.recipeService = recipeService;
  }

  @RequestMapping({"", "/", "/index"})
  public String getIndexPage(Model model)
  {
    model.addAttribute("recipes", recipeService.getRecipes());

//    Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//    Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
//    System.out.println("Category id: " + categoryOptional.get().getId());
//    System.out.println("UOM id: " + unitOfMeasure.get().getId());

    return "index";
  }
}
