
(function($, window, undefined){

  var map = undefined;
  var map_url = 'https://maps.googleapis.com/maps/api/js?';
  var map_param = {
    'v': '3.exp',
    'signed_in': 'true',
    'callback': 'initmap',
    'key': 'AIzaSyBRn6ciA8c873U6B1Rn7oe3TOjWjHhUCsk'
  };

  window.initmap = function() {
    var mapOptions = {
      center: { lat: 0, lng: 0},
      zoom: 8
    };

    map = new google.maps.Map(document.getElementById('map-canvas'),
                              mapOptions);
  };

  $(document).ready(function() {

    var $script = $('<script/>', {
      'type':  'text/javascript',
      'src': map_url + $.param(map_param)
    });

    $('body').append($script);

  });

})(jQuery, window);
