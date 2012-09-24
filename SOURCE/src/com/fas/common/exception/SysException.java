package com.fas.common.exception;

public class SysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述??/DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SysException() {
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述:</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 */
	public SysException(String message) {
		super(message);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述:</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cause
	 */
	public SysException(Throwable cause) {
		super(cause);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述:</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 * @param cause
	 */
	public SysException(String message, Throwable cause) {
		super(message, cause);
	}

}

