package drools.service.implementation;

import drools.model.Ingredient;
import drools.repository.ExaminationRepository;
import drools.repository.IngredientRepository;
import drools.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient findIngredientByText(String text) {
        return ingredientRepository.findIngredientByText(text);
    }

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }
}
