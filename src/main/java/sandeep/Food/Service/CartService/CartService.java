package sandeep.Food.Service.CartService;

import sandeep.Food.Models.Cart;
import sandeep.Food.Models.Cart_Item;
import sandeep.Food.Request.AddCartItemRequest;

public interface CartService {
    public Cart_Item addItemToCart(AddCartItemRequest request, String jwt) throws Exception;

    public Cart_Item updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long id) throws Exception;

    public Cart clearCart(Long id) throws Exception;
}
