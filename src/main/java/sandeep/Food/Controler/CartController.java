package sandeep.Food.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sandeep.Food.Models.Cart;
import sandeep.Food.Models.Cart_Item;
import sandeep.Food.Models.User;
import sandeep.Food.Request.AddCartItemRequest;
import sandeep.Food.Request.UpdateCartItemRequest;
import sandeep.Food.Service.CartService.CartService;
import sandeep.Food.Service.UserSevice;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserSevice userSevice;

    @PostMapping("/cart/add")
    public ResponseEntity<Cart_Item> addItemToCart(@RequestBody AddCartItemRequest request,
                                                   @RequestHeader("Authorization")String jwt) throws Exception{
        Cart_Item cartItem = cartService.addItemToCart(request,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<Cart_Item> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request,
                                                            @RequestHeader("Authorization")String jwt) throws Exception{
        Cart_Item cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                                            @RequestHeader("Authorization")String jwt) throws Exception{
        Cart cart = cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearcart(@RequestHeader("Authorization")String jwt) throws Exception{
        User user = userSevice.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getUserid());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws Exception{

        User user = userSevice.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getUserid());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
