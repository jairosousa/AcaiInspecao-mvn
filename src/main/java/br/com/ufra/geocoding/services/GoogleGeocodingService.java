/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufra.geocoding.services;

import br.com.ufra.util.RemoveAcentosUtil;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Jairo
 */
public class GoogleGeocodingService {

     private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void obterCoordenada(String endereco) {

        endereco = endereco.replace(" ", "+");

        endereco = RemoveAcentosUtil.removerAcentos(endereco);

        URL url;


        try {

            url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + endereco + "&sensor=false");

            JsonReader jsonReader = Json.createReader(new InputStreamReader(url.openStream()));

            JsonObject jsonObject = jsonReader.readObject();

            jsonReader.close();

            String status = jsonObject.getJsonString("status").toString();
                System.out.println("status"+status);
                //Obtem o Array de Resultados provenientes da consulta ao servico da Google
                JsonArray arrayResults = jsonObject.getJsonArray("results");
                //Converte o Array de resultados em uma lista de objetos do tipo JsonObject
                List<JsonObject> results = arrayResults.getValuesAs(JsonObject.class);

            if (!results.isEmpty()) {
                //Acessa a raiz de conteudo, dentro do Array de resultados
                JsonObject result = results.get(0);

                //Acessa o objeto Geometry, que detem os dados de latitude e longitude
                //Obtem os dados de localizacao (latitude e longitude) por meio do acesso ao objeto Location
                JsonObject location = result.getJsonObject("geometry").getJsonObject("location");

                //Obtem o valor do atributo Lat(Latitude) existente no objeto Location
                //Atribui o valor de Lat ao atributo Latitude do objeto coordenadaTO
                this.latitude = (location.getJsonNumber("lat").toString());

                //Obtem o valor do atributo Lng(Longitude) existente no objeto Location
                //Atribui o valor de Lng ao atributo Longitude do objeto coordenadaTO
                this.longitude = (location.getJsonNumber("lng").toString());
                
            }else {
                System.out.println("Endereço não encontrado");
            }

        } catch (MalformedURLException e) {
            System.out.println("Erro: "+e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro: "+e.toString());
            e.printStackTrace();
        }

    }

}
