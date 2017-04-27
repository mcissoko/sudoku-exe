package com.mcissoko.play.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcissoko.play.util.Util;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

public class ApplicationExceptionHandler {
	public static Logger log = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	public static void showError(Thread t, Throwable e) {
		if (Platform.isFxApplicationThread()) {
			Util.showMessage(AlertType.ERROR, "Exception", "Erreur d'exécution", e.getMessage());
		} else {
			log.error("", t);
		}
	}

}
