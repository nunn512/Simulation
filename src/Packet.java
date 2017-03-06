
public class Packet{
	public static final int MAX_TTL = 15;
	public static final int MIN_TTL = -1;
	
	private AgentNode destinationNodeId;	
	private int packetId = 0;
	private String packetType = "";
	private String packet = "";
	
	private AgentNode senderNodId;
	private AgentNode senderHopId;
	
	public boolean flagDone = false; 
	
	private int ttl = 3;
	
	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public static int getMaxTtl() {
		return MAX_TTL;
	}

	public Packet(int msgId, String msgType , String msg, AgentNode msgSenderNodeId, AgentNode msgSenderHopId,   AgentNode msgDestinationNodeId ) {
		// TODO Auto-generated constructor stub
		this.setSenderHopId(msgSenderHopId);
		this.setpacketId(msgId);
		this.setpacketType(msgType);
		this.setSenderNodId(msgSenderNodeId);
		this.setDestinationNodeId(msgDestinationNodeId);
		this.setpacket(msg);
	}
	
	
	public  void packet(int msgId, String msgType , String msg, AgentNode msgSenderNodeId,  AgentNode msgDestinationNodeId ) {
		// TODO Auto-generated constructor stub
		this.setpacketId(msgId);
		this.setpacketType(msgType);
		this.setSenderNodId(msgSenderNodeId);
		this.setDestinationNodeId(msgDestinationNodeId);
		this.setpacket(msg);
	}
	
	public  void setpacket(String msg) {		
			 packet = msg;		
					
	}
	
	public  String getpacket() {
		return packet;		
		
	}
	
	public  void setpacketId(int packetId2) {	
		packetId = packetId2;
				
	}	
	
	public  int getpacketId() {
		return packetId;		
		
	}
	
	public  void setpacketType(String msgType) {	
		packetType = msgType;
				
	}	
	
	public  String getpacketType() {
		return packetType;		
		
	}
	
	public  void setSenderNodId(AgentNode msgSenderNodeId) {	
		senderNodId = msgSenderNodeId;
				
	}	
	
	public  AgentNode getSenderNodId() {
		return senderNodId;		
		
	}
	
	public  void setSenderHopId(AgentNode senderHopId) {	
		this.senderHopId = senderHopId;
	}	
	
	public  AgentNode getSenderHopId() {
		return senderHopId;		
		
	}

	public  AgentNode getDestinationNodeId() {
		return destinationNodeId;
	}

	public  void setDestinationNodeId(AgentNode destinationNodeId) {
		this.destinationNodeId = destinationNodeId;
	}
	

}
