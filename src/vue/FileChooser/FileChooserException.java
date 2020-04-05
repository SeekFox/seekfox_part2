/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;

/**
 * @see Exception
 */
@SuppressWarnings("ThrowableNotThrown")
public class FileChooserException extends Exception {
	public FileChooserException() {
		new Exception();
	}

	public FileChooserException(String message) {
		new Exception(message);
	}

	public FileChooserException(String message, Throwable cause) {
		new Exception(message, cause);
	}

	public FileChooserException(Throwable cause) {
		new Exception(cause);
	}
}
