/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
	initialize();
};

var geocoder;
var map;
var mapOptions = {
    zoom: 17,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

var marker;
function initialize() {
    geocoder = new google.maps.Geocoder();
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    codeAddress();
}
function codeAddress() {
    var address = document.getElementById('logradouro').value+", "+document.getElementById('numero')+" "+document.getElementById('cidade')+" - "+document.getElementById('uf');
    geocoder.geocode({'address': address}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
            if (marker)
                marker.setMap(null);
            marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location,
                draggable: true
            });
            google.maps.event.addListener(marker, "dragend", function () {
                document.getElementById('latitude').value = marker.getPosition().lat();
                document.getElementById('longitude').value = marker.getPosition().lng();
            });
            document.getElementById('latitude').value = marker.getPosition().lat();
            document.getElementById('longitude').value = marker.getPosition().lng();
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}