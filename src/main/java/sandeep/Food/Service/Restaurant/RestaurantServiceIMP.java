package sandeep.Food.Service.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sandeep.Food.Dto.RestaurantDto;
import sandeep.Food.Models.Address;
import sandeep.Food.Models.Restaurant;
import sandeep.Food.Models.User;
import sandeep.Food.Repository.AddressRepository;
import sandeep.Food.Repository.RestaurantRepository;
import sandeep.Food.Repository.UserRepository;
import sandeep.Food.Request.CreateRestaurantRequest;
import sandeep.Food.Service.UserSevice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceIMP implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserSevice userSevice;


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {

        Address address = addressRepository.save(request.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisine_type(request.getCuisineType());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOpeninghours(request.getOpeningHours());
        restaurant.setOwner(user);


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant.getAddress() != null)restaurant.setCuisine_type(updateRestaurant.getCuisineType());
        if (restaurant.getName() != null)restaurant.setName(updateRestaurant.getName());
        if (restaurant.getDescription() != null)restaurant.setDescription(updateRestaurant.getDescription());
        if (restaurant.getContactInformation() != null)restaurant.setContactInformation(updateRestaurant.getContactInformation());
        if (restaurant.getOpeninghours() != null)restaurant.setOpeninghours(updateRestaurant.getOpeningHours());
        if (restaurant.getImages() != null)restaurant.setImages(updateRestaurant.getImages());


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getallRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String  keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant>  optional = restaurantRepository.findById(id);
        if (optional.isEmpty())throw new Exception("Restaurant Not Found With Id : "+id);
        return optional.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findByOwner_Userid(userId);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurant Not Found For this Owner Id: " + userId);
        }
        return restaurant.get();
    }



    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setRestaurantDto_id(restaurantId);

        boolean isFavorited = false;
        List<RestaurantDto> favorites = user.getFavourite();
        for (RestaurantDto favorite : favorites) {
            if (favorite.getRestaurantDto_id() == restaurantId) {  // Compare primitive long values
                isFavorited = true;
                break;
            }
        }

        if (isFavorited)favorites.removeIf(favorite -> favorite.getRestaurantDto_id() == restaurantId);
        else favorites.add(dto);



        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
