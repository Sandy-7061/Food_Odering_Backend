package sandeep.Food.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Category;
import sandeep.Food.Models.User;
import sandeep.Food.Service.Category_Service.CategoryService;
import sandeep.Food.Service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryControler {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserSevice userSevice;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userSevice.findUserByJwtToken(jwt);

        Category createdCategory = categoryService.createCategory(category.getName(), user.getUserid());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userSevice.findUserByJwtToken(jwt);

        List<Category> createdCategory = categoryService.findCategoryByRestaurantId(user.getUserid());
        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }
}
