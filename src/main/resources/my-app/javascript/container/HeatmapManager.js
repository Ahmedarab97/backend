// HeatmapManager.js
import {laaggeletterdheidLayer} from "../layers/laaggeltterdheidLayer";

export default class HeatmapManager {
    constructor(map) {
        this.map = map;
        this.heatmapLayer = null;
        this.createHeatmapLayer();
    }

    async createHeatmapLayer() {
        const postcodes = ['Nieuwegein', 'Jutphaas', 'Boogstede'];
        const thresholdVariable = 20;
        this.heatmapLayer = await laaggeletterdheidLayer(postcodes, thresholdVariable);
        const postcodes2 = ['Jutphaas Wijkersloot', 'Wijkersloot-Oost', 'Wijkersloot-West', 'Zuilenstein'];
        const thresholdVariable2 = 8;
        this.heatmapLayer = await laaggeletterdheidLayer(postcodes2, thresholdVariable2);
    }

    addHeatmapLayerToMap() {
        if (this.heatmapLayer) {
            this.map.addLayer(this.heatmapLayer);
        }
    }

    toggleHeatmapLayer() {
        if (this.heatmapLayer) {
            this.heatmapLayer.setVisible(!this.heatmapLayer.getVisible());
        }
    }
}
