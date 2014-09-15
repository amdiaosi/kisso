/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kisso.testmy;

import java.io.IOException;

import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityRoute;
import wang.leq.sso.CrossDomainHelper;
import wang.leq.sso.LoginHelper;
import wang.leq.sso.SSOToken;

/**
 * 代理登录路由
 * <p>
 * @author   hubin
 * @Date	 2014-9-1 
 */
public class OkLoginRoute extends VelocityRoute {

	protected OkLoginRoute(String path) {
		super(path);
	}

	@Override
	public Object handle(Request request, Response response) {
		String returl = "http://my.web.com:8090/timeout.html";
		String replyTxt = request.raw().getParameter("replyTxt");
		if(replyTxt != null && !"".equals(replyTxt)){
			String userId = CrossDomainHelper.ok(request.raw(), response.raw(), replyTxt, 
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDZmAZgJcQV0XjBOk/CB2nR+AXXyVMdcErLgz5LYb/g/Ar7tiHhYlGk69/mlItDnvHxeV/t7ibEqwvQnlDiM6BsYW/9HBzYWiF54D7hxd2MUWqNit232pS5XlmzurrFmqhSomrR0KKJHoA3HIsIGJ/AT9xzDb93GqPtDm6Yt24fwIDAQAB", 
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKeDkRea/pgX2K6V/eOCQ8kImiqH+QkONx+ubVkvME9mr0I4lElNr8Hl1Q2zEQ+zz4zgDyOWoiCBxSwagBPTate15aMs+uplJL74ScW5gduBYcoQfHURC/ORCFGoa5Y6049p1lhow31yKkKGNTYQYupdlR4HFbqESOPFMwFnG0gQIDAQAB","h2wmABdfM7i3K80mAS");
			if("1001".equals(userId)){
				returl = "http://my.web.com:8090/index.html";
				SSOToken st = new SSOToken();
				st.setUserId(userId);
				LoginHelper.authSSOCookie(request.raw(), response.raw(), st);
			}
		}
		try {
			AjaxHelper.outPrint(response.raw(), "{\"returl\":\""+ returl +"\"}", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
