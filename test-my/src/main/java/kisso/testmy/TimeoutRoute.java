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

/**
 * 超时
 * <p>
 * @author   hubin
 * @Date	 2014-9-1 
 */
public class TimeoutRoute extends VelocityRoute {

	protected TimeoutRoute(String path) {
		super(path);
	}

	@Override
	public Object handle(Request request, Response response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return modelAndView(model, "timeout.html");
	}
}
