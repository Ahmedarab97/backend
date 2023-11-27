// heatmap-container.js
import { LitElement, html, css } from 'lit';

class HeatmapContainer extends LitElement {
    static styles = css`
      :host {
        display: block;
      } 
    `;


    constructor() {
        super();
        this.heatmaps = {}; // Object to store heatmap instances
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener('add-heatmap-layer', (event) => this.handleHeatmapAdded(event));
    }

    handleHeatmapAdded(event) {
        const { heatmapId, layer } = event.detail;
        this.heatmaps[heatmapId] = { layer, visible: true };
        if ('heatmap1' in this.heatmaps) {
            console.log('Heatmap with ID "heatmap1" is in heatmaps object.');
        } else {
            console.log('Heatmap with ID "heatmap1"  NOT in heatmaps object.');
        }
        this.requestUpdate();
    }

    //todo hier komt een heatmap object binnen geen id
    toggleHeatmap(heatmapId) {
        console.log("Heatmaps object:", this.heatmaps);

        if (this.heatmaps[heatmapId] !== undefined) {
            console.log(`Heatmap ${heatmapId} exists in the heatmaps object.`);

            const { layer } = this.heatmaps[heatmapId];
            const currentVisibility = layer.getVisible();
            console.log(`Current visibility of ${heatmapId}:`, currentVisibility);

            // Toggle the visibility state of the layer
            layer.setVisible(!currentVisibility);

            const updatedVisibility = layer.getVisible();
            console.log(`Updated visibility of ${heatmapId}:`, updatedVisibility);

            // Trigger a re-render
            this.requestUpdate();
        } else {
            console.log(`Heatmap ${heatmapId} does not exist in the heatmaps object.`);
        }
    }



    addHeatmapLayer(postalCode, thresholdVariable, heatmapId) {
        // Check if the layer already exists
        const existingLayer = this.heatmaps[heatmapId];
        console.log('heatmap added: ', heatmapId);

        if (existingLayer) {
            // Layer already exists, remove it
            this.heatmaps[heatmapId].visible = false;
            this.requestUpdate();
        } else {
            // Layer doesn't exist, add it
            this.dispatchEvent(new CustomEvent('add-heatmap-layer', {
                detail: { postalCode, thresholdVariable, heatmapId },
                bubbles: true,
                composed: true,
            }));
        }
    }
    addDynamicHeatmap() {
        this.addHeatmapLayer('Boogstede', 10, 'heatmap1');
        console.log('Contents of heatmaps:', JSON.stringify(this.heatmaps, null, 2));
        console.log("Heatmaps object:", this.heatmaps);
        if ('heatmap1' in this.heatmaps) {
            console.log('Heatmap with ID "heatmap1" is in heatmaps object.');
        } else {
            console.log('Heatmap with ID "heatmap1" is NOT in heatmaps object.');
        }
    }

    render() {
        return html`
        <div id="heatmap-container">
            <button @click="${() => this.toggleHeatmap('heatmap1')}">Toggle Heatmap 1</button>
            <button @click="${() => this.toggleHeatmap('heatmap2')}">Toggle Heatmap 2</button>
            <button @click="${() => this.addDynamicHeatmap()}">Add Dynamic Heatmap</button>
            <heatmap-element id="heatmap1" .visible="${this.heatmaps['heatmap1']?.visible}" .heatmapId="${'heatmap1'}"></heatmap-element>
            <heatmap-element id="heatmap2" .visible="${this.heatmaps['heatmap2']?.visible}" .heatmapId="${'heatmap2'}"></heatmap-element>
        </div>
    `;
    }

}

customElements.define('heatmap-container', HeatmapContainer);
