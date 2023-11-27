// main.js
import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import './javascript/container/HeatmapContainer.js';
import './javascript/container/HeatmapOpslag.js';
import './javascript/container/HeatmapManager.js';
import {laaggeletterdheidLayer} from "./javascript/layers/laaggeltterdheidLayer";
import HeatmapManager from "./javascript/container/HeatmapManager";
import {getCoordinatenVanOpenStreetMap} from "./javascript/openstreetmap/openstreetmapAPI";
import {Point} from "ol/geom";
import Feature from "ol/Feature";




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

// document.querySelector('heatmap-container').addEventListener('add-heatmap-layer', async (event) => {
//   const { postalCode, thresholdVariable, heatmapId } = event.detail;
//
//   // Assuming laaggeletterdheidLayer is an asynchronous function that returns a layer
//   const layer = await laaggeletterdheidLayer([postalCode], thresholdVariable);
//
//   map.addLayer(layer);
// });

// const heatmapManager = new HeatmapManager(map);

// window.addMarker = async function() {
//   let layer2 = await laaggeletterdheidLayer(["Park Oudegein"], 17);
//
//   map.addLayer(layer2);
//
//
//   console.log(map.getAllLayers());
// }
// _________________________________________________________________________ NEW layer adding without the use of classes:
// Add the heatmap layer to the map
// JavaScript
let heatmapLayer;


// Function to toggle heatmap layer visibility
function toggleHeatmap() {
  if (heatmapLayer) {
    heatmapLayer.setVisible(toggleCheckbox.checked);
  }
}

// Create the heatmap layer when the page loads
document.addEventListener('DOMContentLoaded', async () => {
  const postcodes = ['Nieuwegein', 'Jutphaas', 'Boogstede'];
  const thresholdVariable = 20; // Set your threshold variable
  heatmapLayer = await laaggeletterdheidLayer(postcodes, thresholdVariable);
  map.addLayer(heatmapLayer);
});

// Set up event listener for the toggle checkbox
const toggleCheckbox = document.getElementById('toggleHeatmapCheckbox');
toggleCheckbox.addEventListener('change', () => {
  toggleHeatmap();
});

let rookLayer;
function toggleRookmap() {
  if (rookLayer) {
    rookLayer.setVisible(toggleCheckbox.checked);
  }
}

// Create the heatmap layer when the page loads
document.addEventListener('DOMContentLoaded', async () => {
  const postcodes = ['Nieuwegein', 'Jutphaas', 'Boogstede'];
  const thresholdVariable = 8; // Set your threshold variable
  rookLayer = await laaggeletterdheidLayer(postcodes, thresholdVariable);
  map.addLayer(rookLayer);
});

// Set up event listener for the toggle checkbox
const toggleChecker = document.getElementById('toggleRookmapCheckbox');
toggleChecker.addEventListener('change', () => {
  toggleRookmap();
});

// ___________________________________________________________________ class heatmap manager

// Function to add heatmap layer outside of the event listener
// async function addHeatmapLayer() {
//   try {
//     await heatmapManager.createHeatmapLayer(); // Ensure the heatmap layer is created
//     heatmapManager.addHeatmapLayerToMap();
//   } catch (error) {
//     console.error('Error adding heatmap layer:', error);
//   }
// }
// // Call the function when needed
// const addHeatmapButton = document.getElementById('addHeatmapButton');
//
// // Add a click event listener to the button
// addHeatmapButton.addEventListener('click', () => {
//   addHeatmapLayer();
// });
//
// // Assuming you have a button with id 'toggleHeatmap'
// const toggleButton = document.getElementById('toggleHeatmap');
// toggleButton.addEventListener('click', () => {
//   heatmapManager.toggleHeatmapLayer();
// });

// ____________________________________________________________________________________ LIT ELEMENTS IMPLEMENTATION WITH CONTROLLER:

// const heatmapManager = new HeatmapManager(map);
// document.querySelector('heatmap-controller').addEventListener('add-heatmap-layer', () => {
//   heatmapManager.addHeatmapLayerToMap();
// });
//
// const toggleButton = document.getElementById('toggleHeatmap');
// toggleButton.addEventListener('click', () => {
//   heatmapManager.toggleHeatmapLayer();
// });
// _______________________________________________________________________ bolletjes
// async function getBuurtenData() {
//     // Simulating an asynchronous operation
//     return [
//         { postcode: 'Leusden', residents: 50 },
//         { postcode: 'Utrecht', residents: 75 },
//         // Add more entries as needed
//     ];
// }
//
//
// async function createHeatmapLayer() {
//     var buurten = await getBuurtenData();
//     console.log(buurten);
//     var points = [];
//
//     for (const buurt of buurten) {
//         let coordinates = await getCoordinatenVanOpenStreetMap(buurt.postcode);
//         let coordinatesCijferObject = {
//             coord: coordinates,
//             cijfer: 100 * (buurt.residents / 100), // Adjust the calculation as needed
//             totaleHuishoudens: 150
//         };
//         points.push(coordinatesCijferObject);
//     }
//
//     console.log(points);
//
//     var vectorSource = new VectorSource();
//
//     var styleFunction = function (feature) {
//         var size = feature.get('pixel');
//
//         return new Style({
//             image: new CircleStyle({
//                 radius: size,
//                 fill: new Fill({
//                     color: feature.get('color')
//                 }),
//                 stroke: new Stroke({
//                     color: 'black',
//                     width: 2
//                 })
//             }),
//             text: new Text({
//                 text: feature.get('label'),
//                 font: '12px Calibri,sans-serif',
//                 fill: new Fill({
//                     color: '#000'
//                 }),
//                 stroke: new Stroke({
//                     color: '#fff',
//                     width: 2
//                 })
//             })
//         });
//     };
//
//     var vectorLayer = new VectorLayer({
//         source: vectorSource,
//         style: styleFunction
//     });
//
//     for (const point of points) {
//         var totaal = point.cijfer;
//         var afgerond = Math.round(totaal);
//         var color = "";
//
//         let size = 0;
//         if (afgerond <= 0) {
//             size += 5;
//             color = "#008000";
//         }
//         else if (afgerond <= 20) {
//             size += 7;
//             color = "#90EE90";
//         }
//         else if (afgerond <= 40) {
//             size += 9;
//             color = "#FFFF00";
//         }
//         // ... (other conditions)
//
//         var feature = new Feature({
//             geometry: new Point(point.coord),
//             pixel: size,
//             label: afgerond.toString(),
//             color: color
//         });
//
//         vectorSource.addFeature(feature);
//     }
//
//     return vectorLayer;
// }
//
// // Assuming you have a button with id 'showHeatmapButton'
// const showHeatmapButton = document.getElementById('showHeatmapButton');
//
// // Add a click event listener to the button
// showHeatmapButton.addEventListener('click', async () => {
//     // Call your createHeatmapLayer function when the button is clicked
//     var heatmapLayer = await createHeatmapLayer();
//
//     // Add the heatmap layer to your map
//     map.addLayer(heatmapLayer);
// });
// _______________________________________________________________________


// const heatmapContainer = document.querySelector('heatmap-container');
// heatmapContainer.addHeatmapLayer('Nieuwegein', 17, 'heatmap1');
// heatmapContainer.addHeatmapLayer('Jutphaas', 8, 'heatmap1');



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