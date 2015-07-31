/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.geocoding.services;

/**
 *
 * @author Jairo
 */
public class TesteCoordenadas {
    
    public static void main(String[] args) {
        
        String end = "Travessa L seis, 223 Icoaraci";
        
        GoogleGeocodingService geocodingService = new GoogleGeocodingService();
        
        geocodingService.obterCoordenada(end);
        
        System.out.println("Latitude: "+geocodingService.getLatitude());
        System.out.println("Longitude: "+geocodingService.getLongitude());
                
    }
    
}
