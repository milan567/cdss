package drools.service;

import drools.model.Ingredient;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);

    Ingredient findIngredientByText(String text);
}
