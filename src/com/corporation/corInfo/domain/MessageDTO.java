package com.corporation.corInfo.domain;

public class MessageDTO {
private corporation_message message;
public MessageDTO(corporation_message message) {

	this.message = message;
}
public corporation_message getMessage() {
	return message;
}

public void setMessage(corporation_message message) {
	this.message = message;
}
@Override
public String toString() {
	return "messageDTO [message=" + message + "]";
}

}
