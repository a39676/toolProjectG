package demo.cloudinary;

public enum ChannelType {
	
	c1("1", 1),
	c9("9", 9),
	c10("10", 10),
	pet("pet", 16),
	;
	
	private String channelTypeName;
	private Integer channelTypeCode;
	
	ChannelType(String channelTypeName, Integer channelTypeCode) {
		this.channelTypeName = channelTypeName;
		this.channelTypeCode = channelTypeCode;
	}
	

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public Integer getChannelTypeCode() {
		return channelTypeCode;
	}

	public static ChannelType getType(String typeName) {
		for(ChannelType t : ChannelType.values()) {
			if(t.getChannelTypeName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
	public static ChannelType getType(Integer typeCode) {
		for(ChannelType t : ChannelType.values()) {
			if(t.getChannelTypeCode().equals(typeCode)) {
				return t;
			}
		}
		return null;
	}
}
