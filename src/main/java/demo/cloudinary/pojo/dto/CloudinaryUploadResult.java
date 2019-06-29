package demo.cloudinary.pojo.dto;

public class CloudinaryUploadResult {

	private String secureUrl;
	private String originalFilename;
	private String publicId;

	@Override
	public String toString() {
		return "CloudinaryUploadResult [secureUrl=" + secureUrl + ", originalFilename=" + originalFilename
				+ ", publicId=" + publicId + "]";
	}

	public String getSecureUrl() {
		return secureUrl;
	}

	public void setSecureUrl(String secureUrl) {
		this.secureUrl = secureUrl;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

}
