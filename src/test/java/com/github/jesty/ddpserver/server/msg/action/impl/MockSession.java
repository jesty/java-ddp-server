package com.github.jesty.ddpserver.server.msg.action.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class MockSession implements Session {

	public List<String> lastText = new LinkedList<String>();

	@Override
	public void addMessageHandler(MessageHandler arg0)
			throws IllegalStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close(CloseReason arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Async getAsyncRemote() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Basic getBasicRemote() {
		// TODO Auto-generated method stub
		return new Basic() {
			
			@Override
			public void setBatchingAllowed(boolean arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendPong(ByteBuffer arg0) throws IOException,
					IllegalArgumentException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendPing(ByteBuffer arg0) throws IOException,
					IllegalArgumentException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean getBatchingAllowed() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void flushBatch() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendText(String arg0, boolean arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendText(String arg0) throws IOException {
				MockSession.this.lastText.add(arg0);
			}
			
			@Override
			public void sendObject(Object arg0) throws IOException, EncodeException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendBinary(ByteBuffer arg0, boolean arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void sendBinary(ByteBuffer arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Writer getSendWriter() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public OutputStream getSendStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public WebSocketContainer getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxBinaryMessageBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getMaxIdleTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxTextMessageBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<MessageHandler> getMessageHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Extension> getNegotiatedExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNegotiatedSubprotocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Session> getOpenSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPathParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocolVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<String>> getRequestParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUserProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeMessageHandler(MessageHandler arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxBinaryMessageBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxIdleTimeout(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaxTextMessageBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

}
