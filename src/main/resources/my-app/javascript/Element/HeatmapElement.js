// heatmap-element.js
import { LitElement, html, css } from 'lit';
import { laaggeletterdheidLayer } from "./layers/laaggeltterdheidLayer";

class HeatmapElement extends LitElement {
    static properties = {
        visible: { type: Boolean },
        heatmapId: { type: String },
    };

    constructor() {
        super();
        this.visible = false;
        this.heatmapId = '';
        console.log("hallo connectedcallback heatmapelement");
    }

    async connectedCallback() {
        console.log("hallo connectedcallback heatmapcontainer");
        super.connectedCallback();
        this.addEventListener('heatmap-layer-added', (event) => this.handleHeatmapAdded(event));
    }

    async handleHeatmapAdded(event) {
        console.log('Heatmap added event received:', event.detail);
        const { heatmapId } = event.detail;
        if (heatmapId === this.heatmapId) {
            console.log('Matching heatmap ID. Adding heatmap layer.');
            await this.addHeatmapLayer();
        }
    }


    async addHeatmapLayer() {
        const postalCode = "Boogstede"; // Replace with the logic to get postal code
        const thresholdVariable = 16; // Replace with the appropriate threshold variable

        try {
            const heatmapLayer = await laaggeletterdheidLayer([postalCode], thresholdVariable);
            this.dispatchEvent(new CustomEvent('heatmap-layer-added', { detail: { heatmapId: this.heatmapId, layer: heatmapLayer } }));
        } catch (error) {
            console.error("Error adding heatmap layer:", error);
        }
    }

    render() {
        console.log('laad ik? heatmap element', this.heatmapId);
        return this.visible
            ? html`<div id="${this.heatmapId}">Heatmap Element</div>`
            : html``;
    }
}

customElements.define('heatmap-element', HeatmapElement);
