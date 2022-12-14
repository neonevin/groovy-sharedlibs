/* Copyright 2011 the original author or authors.
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
package org.wslite.rest

import org.wslite.http.HTTPRequest
import org.wslite.http.HTTPResponse

class RESTContentParseException extends RESTClientException {

    RESTContentParseException(String message, HTTPRequest httpRequest, HTTPResponse httpResponse) {
        super(message, httpRequest, httpResponse)
    }

    RESTContentParseException(String message, Throwable cause, HTTPRequest httpRequest, HTTPResponse httpResponse) {
        super(message, cause, httpRequest, httpResponse)
    }
}
