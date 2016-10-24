package org.tech.base.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;

	public BaseException() {
	}

	private Throwable previousThrowable = null;

	public BaseException(String inMessage) {
		super(inMessage);
	}

	public BaseException(String inMessage, Throwable inThrowable) {
		super(inMessage);
		this.previousThrowable = inThrowable;
	}

	public BaseException(Throwable inThrowable) {
		super(inThrowable);
		this.previousThrowable = inThrowable;
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (this.previousThrowable != null) {
			this.previousThrowable.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream inPrintStream) {
		super.printStackTrace(inPrintStream);
		if (this.previousThrowable != null) {
			this.previousThrowable.printStackTrace(inPrintStream);
		}
	}

	public void printStackTrace(PrintWriter inPrintWriter) {
		super.printStackTrace(inPrintWriter);
		if (this.previousThrowable != null) {
			this.previousThrowable.printStackTrace(inPrintWriter);
		}
	}
}
