package finaltp.fileupload.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDTO {

	private MultipartFile Filedata;

	public MultipartFile getFiledata() {
		return Filedata;
	}

	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}
}
