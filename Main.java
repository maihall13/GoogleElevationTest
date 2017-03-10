package com.maia;

import com.google.maps.*;
import java.util.Scanner;


public class Main {

    static Scanner stringscanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //Set up key to talk to google maps.
        SetGeoContext contextOptions = new SetGeoContext();
        GeoApiContext context = contextOptions.setContext();

        //Ask user for place
        System.out.println("Enter the place you would like to search:");
        //string that will hold user's place the are looking for (will have inputted value, using address for tes
        String searchPlace = stringscanner.nextLine();

        Search place = new Search(searchPlace);
        place.setResultRequest(context);

        while(!place.refineSearch()) {
            place.displayResults();

            System.out.println("\nEnter one of the results or enter another search: ");
            String newSearch = stringscanner.nextLine();

            place.search(newSearch);

            place.setResultRequest(context);
            place.verifySearch();
            place.refineSearch();
        }
        System.out.println("Getting elevation of:");

        place.displayResults();
        place.setLatLng();
        place.getElevation();

        System.out.println(String.format("The elevation of " + place.getPlaceName() + " above sea level is %.2f meters", place.getElevation()));
    }
}