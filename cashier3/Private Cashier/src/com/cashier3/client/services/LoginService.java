package com.cashier3.client.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	void login(String mail, String password) throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
