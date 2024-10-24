package sandeep.Food.Controler.Admin;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.IngrediantsCategory;
import sandeep.Food.Models.IngrediantsItem;
import sandeep.Food.Request.IngredientsCategoryRequest;
import sandeep.Food.Request.IngredientsItemRequest;
import sandeep.Food.Service.IngredientsService.IngredientsService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsControler  {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngrediantsCategory> createIngredientCategory(@RequestBody IngredientsCategoryRequest request) throws  Exception{
        IngrediantsCategory ingrediantsCategory = ingredientsService.createIngrediantsCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(ingrediantsCategory, HttpStatus.CREATED);
    }
    @PostMapping()
    public ResponseEntity<IngrediantsItem> createIngredientItem(@RequestBody IngredientsItemRequest request) throws  Exception{
        IngrediantsItem ingrediantsItem = ingredientsService.createIngrediantsItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(ingrediantsItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}/stoke")
    public ResponseEntity<IngrediantsItem> updateIngrediantStoke(@PathVariable Long id) throws  Exception{
        IngrediantsItem ingrediantsItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingrediantsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngrediantsItem>> getRestaurantIngrediants(@PathVariable Long id) throws  Exception{
        List<IngrediantsItem> ingrediantsItem = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(ingrediantsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngrediantsCategory>> getRestaurantIngrediantsCategory(@PathVariable Long id) throws  Exception{
        List<IngrediantsCategory> ingrediantsItem = ingredientsService.findIngrediantsCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingrediantsItem, HttpStatus.OK);
    }
}
