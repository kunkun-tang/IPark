(function($, window, undefined){

  var map = undefined;
  var MapUrl = 'https://maps.googleapis.com/maps/api/js?';
  var MapParam = {
    'v': '3.exp',
    'signed_in': 'true',
    'callback': 'initmap',
    'key': 'AIzaSyBRn6ciA8c873U6B1Rn7oe3TOjWjHhUCsk'
  };

  var DatUrl = 'dat.json';

  var initpos = { lat: 32.61, lng: -85.48 };
  var radius = 0;

  var cur_marker = undefined;
  var park_markers = [];
  //**********direction variables*****//
  var directionsDisplay = undefined;
  var directionsService = undefined;
  var map;


  /***********************************/

  var reserved = {};

  var test_data = undefined;

  window.initmap = function() {

    var mapOptions = {
      center: initpos,
      zoom: 16
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),
                              mapOptions);
    directionsService = new google.maps.DirectionsService();
    //directions
    directionsDisplay = new google.maps.DirectionsRenderer();
    directionsDisplay.setMap(map);
   

    cur_marker = new google.maps.Marker({
      position: new google.maps.LatLng(initpos.lat, initpos.lng),
      draggable: true,
      map: map
    });

    google.maps.event.addListener(map, 'click', function(event) {
      update(event.latLng);
      if(directionsDisplay!=undefined){
       directionsDisplay.set('directions', null);
      }
    });

    google.maps.event.addListener(cur_marker,'dragend',function(event) {
      update(event.latlng,true);
        if(directionsDisplay!=undefined){
       directionsDisplay.set('directions', null);
      }
    });
    

  };


  //calculate route
function calcRoute(endPosition) {
  
  
  var request = {
      origin: cur_marker.position,
      destination: endPosition,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
      directionsDisplay.setDirections(response);
  });
}

 

  function update(loc, drag) {
    if (!drag)
      cur_marker.setPosition(loc);

    var param = {
      'location': loc,
      'radius': radius
    };

    if (!test_data) {

      $.ajax({
        url: DatUrl,
        dataType: 'json'
      }).success(function(json) {
        var i, test_data = json['value'];

        function shuffle(o) {
          for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
          return o;
        };

        function range(a, b) {
          var res = [];
          var step = a < b ? 1 : -1;
          for (var i = a; i != b; i += step)
            res.push(i);
          return res;
        }

        var rng = shuffle(range(0, test_data.length));

        for (i = 0; i < park_markers.length; ++i)
          remove_marker(park_markers[i]);

        park_markers = [];

        for (i = 0; i < 5; ++i)
          add_marker(test_data[rng[i]]);
      });

    } else {
      var i, test_data = json['value'];

      function shuffle(o) {
        for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
        return o;
      };

      function range(a, b) {
        var res = [];
        var step = a < b ? 1 : -1;
        for (var i = a; i != b; i += step)
          res.push(i);
        return res;
      }

      var rng = shuffle(range(0, test_data.length));

      for (i = 0; i < park_markers.length; ++i)
        remove_marker(park_markers[i]);

      park_markers = [];

      for (i = 0; i < 5; ++i)
        add_marker(test_data[rng[i]]);

    }
  }

  function add_marker(d) {
    var latlng = new google.maps.LatLng(d.Y_Coord, d.X_Coord);
    var marker = new google.maps.Marker({
      position: latlng,
      map: map,
      title: 'Click to zoom'
    });

    park_markers.push(marker);

    var info = new google.maps.InfoWindow({
      position: latlng
    });

    google.maps.event.addListener(marker, 'click', function() {
      info.setContent($('<div/>').append($('<button/>', {
        'type': 'button',
        'class': 'btn btn-default reserve-btn',
        'text': d.reserved ? 'Cancel' : 'Reserve' })).html());
      info.open(map, marker);
      calcRoute(marker.position);


      $('.reserve-btn').click(function(){
        if (d.reserved) {
          swal("Cancelled!", "Your reservation has been cancelled.", "success");
          $(this).text('Reserve');
          d.reserved = false;
        } else {
          swal("Reserved!", "Your reservation has been confirmed.", "success");
          $(this).text('Cancel');
          d.reserved = true;
        }
      });

    });
  }
  

  
  


  function remove_marker(mk) {
    mk.setMap(null);
    mk = null;
  }


  $(document).ready(function() {

    var $script = $('<script/>', {
      'type':  'text/javascript',
      'src': MapUrl + $.param(MapParam)
    });

    $('body').append($script);

  });

})(jQuery, window);
