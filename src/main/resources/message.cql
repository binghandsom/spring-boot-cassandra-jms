 CREATE TABLE springbootcassandrajms."message" (
	uuid uuid,
	source text,
	destination text,
	payloadChannel text,
	payloadFormat text,
	payloadType text,
	payload blob,
	valueDate text,
	whenReceived timeuuid,
	status text,
	PRIMARY KEY (source, destination, payloadType,valueDate,whenReceived,status,uuid)
);