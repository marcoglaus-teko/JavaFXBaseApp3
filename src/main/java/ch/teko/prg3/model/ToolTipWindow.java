package ch.teko.prg3.model;

import javafx.scene.control.Tooltip;

public class ToolTipWindow {
	
//	public ToolTipWindow(String message) {
//		Tooltip tooltip = new Tooltip();
//		tooltip.setText(message);
//		tooltip.setGraphicTextGap(0.0);
//	}
	
	public static Tooltip createToolTip(String message) {
		Tooltip tooltip = new Tooltip();
		tooltip.setText(message);
		tooltip.setGraphicTextGap(0.0);
		
		return tooltip;
	}

}
