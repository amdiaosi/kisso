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

import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityRoute;
import wang.leq.sso.SSOToken;
import wang.leq.sso.client.SSOHelper;

/**
 * 跨域登录成功
 * <p>
 * @author   hubin
 * @Date	 2014-9-3
 */
public class IndexRoute extends VelocityRoute {

	protected IndexRoute(String path) {
		super(path);
	}

	@Override
	public Object handle(Request request, Response response) {
		SSOToken token = (SSOToken) SSOHelper.getToken(request.raw());
		if (token == null) {
			try {
				response.raw().sendRedirect("http://sso.test.com:8080/login.html?ReturnUrl=http%3A%2F%2Fmy.web.com%3A8090%2Fproxylogin.html");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		return modelAndView(model, "index.html");
	}
}
