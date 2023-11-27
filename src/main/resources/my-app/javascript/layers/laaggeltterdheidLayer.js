import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import { Point } from 'ol/geom';
import { Vector } from 'ol/source';
import { Vector as VectorLayer } from 'ol/layer';
import {Heatmap} from 'ol/layer'
import {getCoordinatenVanOpenStreetMap} from "../openstreetmap/openstreetmapAPI";
import {forEach} from "ol/geom/flat/segments";
import {Fill, Stroke, Style} from "ol/style";
import CircleStyle from "ol/style/Circle";

export async function laaggeletterdheidLayer(postcodes, thresholdVariable) {
    try {
        let coordinates = await Promise.all(postcodes.map(postcode => getCoordinatenVanOpenStreetMap(postcode)));
        console.log(coordinates);

        // Determine the gradient based on the thresholdVariable
        let gradientColors = (thresholdVariable > 15) ?
            ['rgba(255,0,0,0)', 'rgb(255,0,0)', 'rgb(255,100,100)', 'rgb(255,150,150)', 'rgb(255,200,200)', 'rgb(255,255,255)'] :
            ['rgba(33,102,172,0)', 'rgb(70,178,24)', 'rgb(224,240,209)', 'rgb(219,253,199)', 'rgb(166,239,98)', 'rgb(86,178,24)', 'rgb(38,103,0)'];

        var heatmapLayer = new Heatmap({
            source: new Vector({
                features: coordinates.map(function (coord) {
                    return new Feature({
                        geometry: new Point(coord)
                    });
                }),
            }),
            blur: 15,
            radius: 10,
            fill: new Fill({
                color: 'green'
            }),
            gradient: gradientColors,
        });
        return heatmapLayer;
    } catch {
        console.error("er gaat wel iets fout in dit stukje code");
    }
}

