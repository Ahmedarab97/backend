import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { Vector } from 'ol/source';
import { Vector as VectorLayer } from 'ol/layer';
import {Heatmap} from 'ol/layer'
import {pakBoundaries, pakCoordinaten} from "./javascript/layers/laaggeltterdheidLayer";

var coordinates = [
  [5.0908, 52.0305],
  [5.1000, 52.0320]

];

var heatmapLayer = new Heatmap({
  source: new Vector({
    features: coordinates.map(function(coord) {
      return new Feature({
        geometry: new Point(ol.proj.fromLonLat(coord))
      });
    }),
    blur: 15,
    radius: 5,
    gradient: ['blue', 'blue', 'blue']
  })
});

const map = new Map({
  target: 'map',
  layers: [
    new TileLayer({
      source: new OSM()
    }),
  ],
  view: new View({
    center: ol.proj.fromLonLat([5.0908, 52.0305]),
    zoom: 12
  })
});

function addMarkerByPostalCode(postalCode) {
  // Use Nominatim Geocoding API to get coordinates from postal code
  const geocodingUrl = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(postalCode)}`;

  fetch(geocodingUrl)
      .then(response => response.json())
      .then(data => {
        console.log(data)
        if (data.length > 0) {
          const result = data[0];
          const coordinates = fromLonLat([parseFloat(result.lon), parseFloat(result.lat)]);

          const marker = new Feature({
            geometry: new Point(coordinates)
          });

          const markerLayer = new VectorLayer({
            source: new Vector({
              features: [marker]
            })
          });

          map.addLayer(markerLayer);
          map.getView().setCenter(coordinates);
          map.getView().setZoom(13);
        } else {
          console.error('No results found for the postal code');
        }
      })
      .catch(error => console.error('Error fetching data:', error));
}

window.addMarker = function() {
  const postalCodeInput = document.getElementById('postalCodeInput');
  const postalCode = postalCodeInput.value;
  addMarkerByPostalCode(postalCode);
  map.addLayer(heatmapLayer);
}
var tekst = document.getElementById("dataTekst");
fetch("http://localhost:8080/fitheid").then(response => response.json())
    .then(data => {
      tekst.innerHTML = data.kleur;
    })

pakCoordinaten();
pakBoundaries();

// var map = L.map('map').setView([51.505, -0.09], 13);
// L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
//     maxZoom: 19,
//     attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
// }).addTo(map);
// L.Control.geocoder().addTo(map);