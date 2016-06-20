package org.eclipse.rcptt.tesla.recording.aspects.swt.rap;

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
	private String handlerId;

	public ServiceHandlerWrapper(ServiceHandler wrapped, String handlerId) {
		this.wrapper = wrapped;
		this.handlerId = handlerId;
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		final OutputStreamResponseWrapper wrappedResponse = new OutputStreamResponseWrapper(response, true);

		wrapper.service(request, wrappedResponse);
		final ByteArrayOutputStream spyStream = wrappedResponse.getSpyOutputStream();

		final String content = Base64.encode(spyStream.toByteArray());

		final String url = request.getRequestURL() + "?" + request.getQueryString(); //$NON-NLS-1$
		SWTEventManager.recordRapDownload(handlerId, url, content);

	}

}
