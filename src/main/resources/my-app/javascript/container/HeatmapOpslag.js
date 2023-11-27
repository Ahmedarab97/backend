import { LitElement, html, css } from 'lit';

class HeatmapOpslag extends LitElement {
    static styles = css`
      :host {
        display: block;
      } 
    `;
    constructor(layer1, layer2){
        super();
        this.heatmaps = {layer1,layer2};
    }
}

customElements.define('heatmap-opslag', HeatmapOpslag);