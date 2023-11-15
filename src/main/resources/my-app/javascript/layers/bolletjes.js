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

async function getBuurtenData() {
    await fetch("http://localhost:8080/gemeente/nieuwegein/buurt").then()
}

export async function bolletjesLayer() {
    //TODO hier een statement schrijven die alle data van eva ophaalt en die gebruiken voor het maken van de buurten nu nog hardcoded
    var buurten = [{buurtnaam: "Binnenweides nieuwegein", score: 10}
        , {buurtnaam: "Blokhoeve nieuwegein", score: 5}
        , {buurtnaam: "Buitengebied Laagraven nieuwegein", score: 15}
        , {buurtnaam: "Burgen nieuwegein", score: 11}
        , {buurtnaam: "De Wiers nieuwegein", score: 9}
        , {buurtnaam: "Hagen nieuwegein", score: 7}
        , {buurtnaam: "Jutphaas nieuwegein", score: 25}
        , {buurtnaam: "Merwestein nieuwegein", score: 23}
        , {buurtnaam: "Zuilenstein nieuwegein", score: 18}];

    var points = [];

    for (const buurt of buurten) {
        var coordinates = await getCoordinatenVanGoogleMaps(buurt.buurtnaam);
        let coordinatesCijferObject = {
            coord: coordinates,
            cijfer: buurt.score
        }
        points.push(coordinatesCijferObject)
        //dit pushed iets in de trant van [{coord: [1, 1.00012], cijfer: 5}]
    }

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
        if (point.coord === undefined) {
            continue;
        }
        let size = 0;
        if (point.cijfer <= 5) {
            size += 15;
        }
        else if (point.cijfer <= 10) {
            size += 20;
        }
        else if (point.cijfer <= 12) {
            size += 25;
        }
        else if (point.cijfer <= 15) {
            size += 30;
        }
        else if (point.cijfer <= 17) {
            size += 35;
        }
        else if (point.cijfer <= 20) {
            size += 40;
        } else {
            size += 45;
        }

        var feature = new Feature({
            geometry: new Point(point.coord),
            fixedPixelSize: size,
            label: point.cijfer.toString()
        });

        vectorSource.addFeature(feature);
    }
    return vectorLayer;
}


