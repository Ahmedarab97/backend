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
import {forEach} from "ol/geom/flat/segments";

export async function pakCoordinaten(postcode) {
    const coordinaten = await getCoordinatenVanOpenStreetMap(postcode);
    return coordinaten;
}

export async function laaggeletterdheidLayer(postcodes) {
    try{
        let coordinates =  await Promise.all(postcodes.map(postcode => pakCoordinaten(postcode)));
        console.log(coordinates);
        // for(let index = 0; index < postcodes.length; index++) {
        //     let coordinate = await pakCoordinaten(postcodes[index]);
        //     console.log(coordinate)
        //     coordinates.push(coordinate);
        // }
        var heatmapLayer = new Heatmap({
            source: new Vector({
                features: coordinates.map(function(coord) {
                    return new Feature({
                        geometry: new Point(fromLonLat(coord))
                    });
                }),
                blur: 15,
                radius: 5,
                gradient: ['blue', 'green', 'yellow', 'red']
            })
        });
        heatmapLayer.setZIndex(1);
        // const features = await heatmapLayer.getSource().getFeatures()
        // features.forEach(feature => {
        //     const coordinates = feature.getGeometry().getCoordinates();
        //     console.log(coordinates);
        // });
        return heatmapLayer;
    }
    catch {
        console.error("er gaat wel iets fout in dit stukje code");
    }

}


