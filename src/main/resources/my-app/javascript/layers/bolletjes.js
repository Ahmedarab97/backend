import { Map, View } from 'ol';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import {Circle, Point} from 'ol/geom';

import { Vector as VectorLayer } from 'ol/layer';
import {Heatmap} from 'ol/layer'
import {getCoordinatenVanGoogleMaps, getCoordinatenVanOpenStreetMap} from "../openstreetmap/openstreetmapAPI";
import {forEach} from "ol/geom/flat/segments";
import {Fill, Stroke, Style} from "ol/style";
import CircleStyle from "ol/style/Circle";
import { Vector } from 'ol/source';
import VectorSource from "ol/source/Vector";
import Text from 'ol/style/Text';
import * as math from "ol/math";

async function getBuurtenData() {
    const apiUrl = "http://localhost:8080/gemeente/Nieuwegein";
    try {
        const response = await fetch(apiUrl);
        if (response.ok) {
            const data = await response.json();
            console.log(data);
            return data;
        } else {
            console.error("Fout bij ophalen data:", response.status);
        }
    } catch (error) {
        console.error("Fout bij ophalen data:", error);
    }
}

export async function bolletjesLayer() {

    var buurten = await getBuurtenData();
    console.log(buurten);
    var points = [];

    for (const buurt of buurten.wijken) {
        if (buurt.wijkInfo !== null && buurt.wijkInfo.laagGeletterdheid.percentageTaalgroei !== null) {
            console.log(buurt.wijkInfo.laagGeletterdheid.percentageTaalgroei);
            var coordinates = await getCoordinatenVanGoogleMaps(buurt.naam + " Nieuwegein");
            let coordinatesCijferObject = {
                coord: coordinates,
                cijfer: buurt.wijkInfo.laagGeletterdheid.percentageTaalgroei,
                totaleHuishoudens: buurt.aantalInwoners
            }
            points.push(coordinatesCijferObject)
            //dit pushed iets in de trant van [{coord: [1, 1.00012], cijfer: 5}]
        }
    }

    console.log(points);

    console.log(points);


    var vectorSource = new VectorSource();

    var styleFunction = function (feature) {
        var size = feature.get('fixedPixelSize');

        return new Style({
            image: new CircleStyle({
                radius: size,
                fill: new Fill({
                    color: 'gray'
                }),
                stroke: new Stroke({
                    color: 'black',
                    width: 2
                })
            }),
            text: new Text({
                text: feature.get('label'),
                font: '12px Calibri,sans-serif',
                fill: new Fill({
                    color: '#000'
                }),
                stroke: new Stroke({
                    color: '#fff',
                    width: 2
                })
            })
        });
    };

    var vectorLayer = new VectorLayer({
        source: vectorSource,
        style: styleFunction
    });


    for(const point of points) {
        var totaal = point.totaleHuishoudens * point.cijfer;
        var afgerond = Math.round(totaal);
        console.log(totaal);
        if (point.coord === undefined) {
            continue;
        }
        let size = 0;
        if(afgerond <= 0) {
            size += 5;
        }
        else if (afgerond <= 20) {
            size += 7;
        }
        else if (afgerond <= 40) {
            size += 9;
        }
        else if (afgerond <= 60) {
            size += 12;
        }
        else if (afgerond <= 80) {
            size += 15;
        }
        else if (afgerond <= 100) {
            size += 18;
        }
        else if (afgerond <= 120) {
            size += 20;
        } else {
            size += 22;
        }

        var feature = new Feature({
            geometry: new Point(point.coord),
            fixedPixelSize: size,
            label: afgerond.toString()
        });

        vectorSource.addFeature(feature);
    }
    return vectorLayer;
}


