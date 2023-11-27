// heatmap-controller.js
import { LitElement, html, css } from 'lit';
import HeatmapManager from "../container/HeatmapManager";

class HeatmapController extends LitElement {
    static styles = css`
      /* Your styles here */
      .heatmap-controls {
        position: absolute;
        top: 10px;
        left: 10px;
        background-color: white;
        padding: 10px;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }
    `;

    static properties = {
        heatmapManager: { type: Object },
    };

    connectedCallback() {
        super.connectedCallback();
        this.heatmapManager = window.heatmapManager;  // Access heatmapManager from the global scope
    }

    render() {
        return html`
      <div class="heatmap-controls">
        <label>
          <input type="checkbox" @change="${this.toggleHeatmapLayer}" ?checked="${this.heatmapManager.isHeatmapVisible()}">
          Toggle Heatmap Layer
        </label>
      </div>
    `;
    }

    toggleHeatmapLayer() {
        this.heatmapManager.toggleHeatmapLayer();
    }
}

customElements.define('heatmap-controller', HeatmapController);
