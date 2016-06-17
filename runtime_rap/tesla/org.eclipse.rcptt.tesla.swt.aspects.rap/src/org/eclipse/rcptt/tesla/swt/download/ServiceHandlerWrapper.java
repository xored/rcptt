package org.eclipse.rcptt.tesla.swt.download;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.rap.rwt.service.ServiceHandler;
import org.eclipse.rcptt.util.Base64;
import org.eclipse.rcptt.util.OutputStreamResponseWrapper;

public class ServiceHandlerWrapper implements ServiceHandler {

	private ServiceHandler wrapper;

	public ServiceHandlerWrapper(ServiceHandler wrapped) {
		this.wrapper = wrapped;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		final OutputStreamResponseWrapper wrappedResponse = new OutputStreamResponseWrapper(response, false);

		wrapper.service(request, wrappedResponse);
		final ByteArrayOutputStream spyStream = wrappedResponse.getSpyOutputStream();
		final String content = Base64.encode(spyStream.toByteArray());

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentLength(0);
		response.setContentType("text/cmd");
		response.setHeader("Content-Disposition", "");
		//response.getWriter().write("{}");

		RapDownloadHandlerManager.addFile(null, content);

	}

}
