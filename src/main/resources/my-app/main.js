import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import {laaggeletterdheidLayer} from "./javascript/layers/laaggeltterdheidLayer";
import VectorSource from "ol/source/Vector";
import VectorLayer from "ol/layer/Vector";
import {Point} from "ol/geom";
import Feature from "ol/Feature";
import {Heatmap} from "ol/layer";

var coordinates = [
  [5.0908, 52.0305],
  [5.1000, 52.0320]

];


let map = new Map({
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


window.addMarker = async function() {
  let layer2 = await laaggeletterdheidLayer(["Park Oudegein"]);
  map.addLayer(layer2);
  console.log(map.getAllLayers());
}
// pakCoordinaten();
//, "3431BB", "3431BC", "3431BD", "3431BE", "3431BM", "3431CA", "3431CB", "3431CC"

// var map = L.map('map').setView([51.505, -0.09], 13);
// L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
//     maxZoom: 19,
//     attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
// }).addTo(map);
// L.Control.geocoder().addTo(map);


// function addMarkerByPostalCode(postalCode) {
//   // Use Nominatim Geocoding API to get coordinates from postal code
//   const geocodingUrl = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(postalCode)}`;
//
//   fetch(geocodingUrl)
//       .then(response => response.json())
//       .then(data => {
//         console.log(data)
//         if (data.length > 0) {
//           const result = data[0];
//           const coordinates = fromLonLat([parseFloat(result.lon), parseFloat(result.lat)]);
//
//           const marker = new Feature({
//             geometry: new Point(coordinates)
//           });
//
//           const markerLayer = new VectorLayer({
//             source: new Vector({
//               features: [marker]
//             })
//           });
//
//           map.addLayer(markerLayer);
//           map.getView().setCenter(coordinates);
//           map.getView().setZoom(13);
//         } else {
//           console.error('No results found for the postal code');
//         }
//       })
//       .catch(error => console.error('Error fetching data:', error));
// }