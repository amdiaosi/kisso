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
import wang.leq.sso.AuthToken;
import wang.leq.sso.CrossDomainHelper;

/**
 * 代理登录路由
 * <p>
 * @author   hubin
 * @Date	 2014-9-1 
 */
public class ProxyLoginRoute extends VelocityRoute {

	protected ProxyLoginRoute(String path) {
		super(path);
	}

	@Override
	public Object handle(Request request, Response response) {
		Map<String, Object> model = new HashMap<String, Object>();
		AuthToken at = new AuthToken(request.raw(), "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAINmYBmAlxBXReME6T8IHadH4BdfJUx1wSsuDPkthv+D8Cvu2IeFiUaTr3+aUi0Oe8fF5X+3uJsSrC9CeUOIzoGxhb/0cHNhaIXngPuHF3YxRao2K3bfalLleWbO6usWaqFKiatHQookegDcciwgYn8BP3HMNv3cao+0Obpi3bh/AgMBAAECgYBPbtn1ULkBleCXpmZiRkO21kpvloLzv9OwiLwq/gy39kiAJnkbI+yij7DuEJpQxoqpb8aW+LdOd3FDeInZ+5/qAW5wdMgvABIINxpsQvED9RWb8NvL7Hqvv/ByMFONhyxdbzRDEVcR6SG7pGTJ1NY4TvfUmLDJrXa5N1LZ5lwZoQJBAN2bUnqVwWaQA5V7ZCEPYKAu6rQj0W6fKzcM84Q7dFskF7ZN8lK2zDtiyH9HTYCPtg51rdtIjZ6O2TVz8pDfp/cCQQCXywmR8HxLWhpvQ28e0LbqPMZrP2FBPo9Fe/UneIOtFhi1bC78xvY3b00a7qHSca8UuWGdzI0FPPhaRsLjfaG5AkEAi1gUR8KMxqn9puvcrTEXKAH4UOdI1I8/RDFfmiEsa/bI9jgTDFGnIBxgSDAUmqdC6dqzRHRdoHrgN809lD0eRwJAN2XEUlzAIAf8ScsEjOyDNS2FBLMW3WblhuhcalFTTSIZVmzrIRnD1itqqn+Y02LmENwvQhXbCaRcxyW4DqpVEQJABMSyhR1YzeXHmRVs3k8+eQOZPitUKsArs/tSbt5VB9Ym98anq7VNIXrFDmKsEQTuoIamva94yF7vu1HcrR/LiQ==");
		model.put("askurl", "http://sso.test.com:8080/asklogin.html?askTxt=" + CrossDomainHelper.askCiphertext(at, "h2wmABdfM7i3K80mAS"));
		model.put("okurl", "http://my.web.com:8090/oklogin.html");
		CrossDomainHelper.setAuthCookie(request.raw(), response.raw(), at);
		return modelAndView(model, "proxyLogin.html");
	}
}
