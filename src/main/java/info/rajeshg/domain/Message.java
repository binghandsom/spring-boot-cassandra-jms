package info.rajeshg.domain;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.DataType.Name;

/**
 * 
 CREATE TABLE springbootcassandrajms."Message" (
	uuid uuid,
	source text,
	destination text,
	payloadChannel text,
	payloadFormat text,
	payloadType text,
	payload blob,
	valueDate timestamp,
	whenReceived timeuuid,
	status text,
	PRIMARY KEY (source, destination, payloadType,valueDate,whenReceived,status,uuid)
);
 * 
 */

@Table(value="message")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String source;// entity name: ICB, SMA, ICN, INT
	
	@PrimaryKeyColumn(name = "uuid", ordinal = 6, type = PrimaryKeyType.CLUSTERED)
	@CassandraType(type = Name.UUID)
	private UUID uuid;// universal unique identifier
	
	@PrimaryKeyColumn(name = "destination", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private String destination;// entity name: ICB,SMA, ICN, INT
	
	@PrimaryKeyColumn(name = "payloadType", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private String payloadType; // pain813, pacs814, camt54...
		
	@PrimaryKeyColumn(name = "valueDate", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
	private String valueDate;
	
	@PrimaryKeyColumn(name = "whenReceived", ordinal = 4, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	@CassandraType(type = Name.TIMEUUID)
	private UUID whenReceived;// timeuuid
	
	@PrimaryKeyColumn(name = "status", ordinal = 5, type = PrimaryKeyType.CLUSTERED)
	private String status;// NEW, BEING PROCESSED (VALIDATED / SENT to DEST),
							// PROCESSED (SUCCESS:Accepted by DEST / FAILED:
							// Rejected by DEST)

	@Column
	private String payloadChannel; // jms,http, file...
	
	@Column
	private String payloadFormat; // json, xml, csv...
	
	@Column
	@CassandraType(type = Name.BLOB)
	private ByteBuffer payload;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPayloadChannel() {
		return payloadChannel;
	}

	public void setPayloadChannel(String payloadChannel) {
		this.payloadChannel = payloadChannel;
	}

	public String getPayloadFormat() {
		return payloadFormat;
	}

	public void setPayloadFormat(String payloadFormat) {
		this.payloadFormat = payloadFormat;
	}

	public String getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(String payloadType) {
		this.payloadType = payloadType;
	}

	public ByteBuffer getPayload() {
		return payload;
	}

	public void setPayload(ByteBuffer payload) {
		this.payload = payload;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public UUID getWhenReceived() {
		return whenReceived;
	}

	public void setWhenReceived(UUID whenReceived) {
		this.whenReceived = whenReceived;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
