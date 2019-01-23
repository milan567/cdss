package drools.service;

import drools.model.Ingredient;

import java.util.List;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);

    Ingredient findIngredientByText(String text);

    List<Ingredient> findAllIngredients();
}
