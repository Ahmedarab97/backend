import { LitElement, html, css } from 'lit';

class MyElement extends LitElement {
    static styles = css`
    :host {
      display: block;
      padding: 16px;
      border: 1px solid #ddd;
      background-color: #f9f9f9;
    }
  `;

    render() {
        return html`
      <h2>Hello from Lit Element!</h2>
      <p>This is a basic Lit Element component.</p>
    `;
    }
}

customElements.define('my-element', MyElement);
