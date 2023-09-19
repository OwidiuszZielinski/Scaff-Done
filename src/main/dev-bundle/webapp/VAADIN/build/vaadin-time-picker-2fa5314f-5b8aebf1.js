import{inputFieldProperties as o,labelProperties as p,helperTextProperties as s,errorMessageProperties as l}from"./vaadin-text-field-e82c445d-00538e79.js";import{b as a,U as r,F as i}from"./indexhtml-4a440549.js";const d={tagName:"vaadin-time-picker",displayName:"TimePicker",elements:[{selector:"vaadin-time-picker::part(input-field)",displayName:"Input field",properties:o},{selector:"vaadin-time-picker::part(toggle-button)",displayName:"Toggle button",properties:[a.iconColor,a.iconSize]},{selector:"vaadin-time-picker::part(label)",displayName:"Label",properties:p},{selector:"vaadin-time-picker::part(helper-text)",displayName:"Helper text",properties:s},{selector:"vaadin-time-picker::part(error-message)",displayName:"Error message",properties:l},{selector:"vaadin-time-picker-overlay::part(overlay)",displayName:"Overlay",properties:[r.backgroundColor,r.borderColor,r.borderWidth,r.borderRadius,r.padding]},{selector:"vaadin-time-picker-overlay vaadin-time-picker-item",displayName:"Overlay items",properties:[i.textColor,i.fontSize,i.fontWeight]},{selector:"vaadin-time-picker-overlay vaadin-time-picker-item::part(checkmark)::before",displayName:"Overlay item checkmark",properties:[a.iconColor,a.iconSize]}],async setupElement(e){e.overlayClass=e.getAttribute("class"),e.value="00:00",e.opened=!0,await new Promise(t=>setTimeout(t,10))},async cleanupElement(e){e.opened=!1}};export{d as default};
