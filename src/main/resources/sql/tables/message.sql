CREATE TABLE L3G3_message (
	message_id number NOT NULL,
	receiver_id number NOT NULL,
	sender_id number NOT NULL,
	message varchar2(255) NOT NULL,
	constraint MESSAGE_PK PRIMARY KEY (message_id)
);
