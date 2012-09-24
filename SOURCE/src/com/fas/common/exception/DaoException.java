package com.fas.common.exception;

import java.sql.SQLException;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述:</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public DaoException() {
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述??/DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 */
	public DaoException(String message) {
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
	public DaoException(Throwable cause) {
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
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述:</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param e
	 */
	public DaoException(SQLException e) {
		super(e.getMessage(), e.getCause());
	}

}

