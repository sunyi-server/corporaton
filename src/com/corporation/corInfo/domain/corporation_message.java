package com.corporation.corInfo.domain;

public class corporation_message {
	private String message_id;
	private String corporation_id;
	private String corporation_name;
	private String message_content;
	private String message_gmt_create;
	private String message_gmt_modified;

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getCorporation_id() {
		return corporation_id;
	}

	public void setCorporation_id(String corporation_id) {
		this.corporation_id = corporation_id;
	}

	public String getCorporation_name() {
		return corporation_name;
	}

	public void setCorporation_name(String corporation_name) {
		this.corporation_name = corporation_name;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_gmt_create() {
		return message_gmt_create;
	}

	public void setMessage_gmt_create(String message_gmt_create) {
		this.message_gmt_create = message_gmt_create;
	}

	public String getMessage_gmt_modified() {
		return message_gmt_modified;
	}

	public void setMessage_gmt_modified(String message_gmt_modified) {
		this.message_gmt_modified = message_gmt_modified;
	}

	@Override
	public String toString() {
		return "corporation_message [message_id=" + message_id + ", corporation_id=" + corporation_id
				+ ", corporation_name=" + corporation_name + ", message_content=" + message_content
				+ ", message_gmt_create=" + message_gmt_create + ", message_gmt_modified=" + message_gmt_modified + "]";
	}

}
