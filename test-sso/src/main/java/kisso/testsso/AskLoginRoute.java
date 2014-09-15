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
package kisso.testsso;

import java.io.IOException;

import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityRoute;
import wang.leq.sso.CrossDomainHelper;
import wang.leq.sso.SSOToken;
import wang.leq.sso.client.SSOHelper;

/**
 * 询问是否登录
 * <p>
 * @author   hubin
 * @Date	 2014-9-1 
 */
public class AskLoginRoute extends VelocityRoute {

	protected AskLoginRoute(String path) {
		super(path);
	}

	@Override
	public Object handle(Request request, Response response) {
		StringBuffer replyTxt = new StringBuffer();
		replyTxt.append(request.raw().getParameter("callback")).append("({\"msg\":\"");
		SSOToken token = (SSOToken) SSOHelper.getToken(request.raw());
		if (token != null) {
			String askTxt = request.raw().getParameter("askTxt");
			if(askTxt != null && !"".equals(askTxt)){
				replyTxt.append(CrossDomainHelper.replyCiphertext(request.raw(), "1001", askTxt, 
						"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMp4ORF5r+mBfYrpX944JDyQiaKof5CQ43H65tWS8wT2avQjiUSU2vweXVDbMRD7PPjOAPI5aiIIHFLBqAE9Nq17Xloyz66mUkvvhJxbmB24FhyhB8dREL85EIUahrljrTj2nWWGjDfXIqQoY1NhBi6l2VHgcVuoRI48UzAWcbSBAgMBAAECgYAtLeaOH7lBQcPh23GpBJ4RZa9QvIi6mZonNPWNct0HnnT/RW67/vtehugLwt2QDH/uhQlxA57LOUQYs13p6N7qMZ+4YY592hw4hrJUEAuuORU+wKWnr+wVQNm6Qc9Qf7axM6B5NgtLPbf0R7M53vgHHMyJh2tJKrY3RUdBbsUugQJBAObj3+B7v2QVKKPZlYvICwbKZAUcb1qZtPjtw7+aDah0EEqkaYD0ytmjl2esoknPySN2gbouc+nDvYZopFLgiDMCQQDgfRqCYfMHhjHPHoOwco3ZAevDDe22QksBIkfgFB9srEJCWauFyvB5PTG6+wFv94zqy3R92C6AVaWn8Ae8uqx7AkBkroWXfB7PY7KfEGh31bmJMoQ+/lFIbrJNwlCTonfGNyZLhjpDc3tpQD7rhIoYKbWJ80lKiKsfCq4AiGzvft2lAkEAqcBQDGmu0XC7N2hWolVtR7x5H8znhNuKRfg7K4lr3cxAalXOKuSzhKoucbqecqFZsK5aj1Kqjya0llIeN6tdAwJAImLxsxLxhk6dc8slEo8ObLAWWWkRZNiXCpr+2aWspVx1cK3GRtAa+0Q7X0TiA62/CrlWR/xJHvDI/+I9mcxJKg==", 
						"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDZmAZgJcQV0XjBOk/CB2nR+AXXyVMdcErLgz5LYb/g/Ar7tiHhYlGk69/mlItDnvHxeV/t7ibEqwvQnlDiM6BsYW/9HBzYWiF54D7hxd2MUWqNit232pS5XlmzurrFmqhSomrR0KKJHoA3HIsIGJ/AT9xzDb93GqPtDm6Yt24fwIDAQAB", "h2wmABdfM7i3K80mAS"));
			}
		}else{
			//未登录
			replyTxt.append("-1");
		}
		try {
			replyTxt.append("\"})");
			AjaxHelper.outPrint(response.raw(), replyTxt.toString(), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
