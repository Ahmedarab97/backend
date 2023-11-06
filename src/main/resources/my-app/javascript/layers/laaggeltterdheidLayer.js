import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { Vector } from 'ol/source';
import { Vector as VectorLayer } from 'ol/layer';
import {Heatmap} from 'ol/layer'
import {getCoordinatenVanOpenStreetMap} from "../openstreetmap/openstreetmapAPI";

export async function pakCoordinaten() {
    const coordinaten = await getCoordinatenVanOpenStreetMap("Raadstede");
    console.log(coordinaten);
}


// var heatmapLayer = new Heatmap({
//     source: new Vector({
//         features: coordinates.map(function(coord) {
//             return new Feature({
//                 geometry: new Point(ol.proj.fromLonLat(coord))
//             });
//         }),
//         blur: 15,
//         radius: 5,
//         gradient: ['blue', 'blue', 'blue']
//     })
// });

