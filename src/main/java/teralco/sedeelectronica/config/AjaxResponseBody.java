package teralco.sedeelectronica.config;

import java.util.List;

public class AjaxResponseBody {

	String msg;
	private Object data;
	private List<String> errorMessageList;

	public AjaxResponseBody() {

	}

	public AjaxResponseBody(String pMsg, Object pData) {
		super();
		this.msg = pMsg;
		this.data = pData;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String pMsg) {
		this.msg = pMsg;
	}

	public List<String> getErrorMessageList() {
		return this.errorMessageList;
	}

	public void setErrorMessageList(List<String> pErrorMessageList) {
		this.errorMessageList = pErrorMessageList;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object pData) {
		this.data = pData;
	}
}
