package com.github.jesty.ddpserver.model.error;

import com.github.jesty.ddpserver.model.HasMsg;

/**
 * @author davidecerbo
 *
 * Messages:
 * error: string (previously a number. See appendix 3)
 * reason: optional string
 * details: optional string
 *
 */
public class Error  implements HasMsg {
	
	public static final String MSG = "error";
	
	private String error;
	private String reason;
	private String details;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Override
	public String getMsg() {
		return MSG;
	}

}