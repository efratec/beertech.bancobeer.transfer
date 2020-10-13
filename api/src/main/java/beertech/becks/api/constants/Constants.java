package beertech.becks.api.constants;

public interface Constants {

    interface Messages {
        String STATUS_200_GET_OK = "Successfully retrieved";
        String STATUS_201_CREATED = "Successfully created";
        String STATUS_204_NO_CONTENT = "No Content";
        String STATUS_400_BAD_REQUEST = "Resource is invalid";
        String STATUS_404_NOTFOUND = "Resource not found";
        String STATUS_500_INTERNAL_SERVER_ERROR = "The application has encountered an unknown error. Please try again.";
    }

    interface NUMBERS {
        Long NUMBER_1L = 1L;
    }

    interface Hash {
        String HASH_MD5 = "MD5";
    }


}
