/* Copyright 2011-2014 the original author or authors.
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

import org.wslite.http.*
import org.wslite.http.auth.*

class RESTClient {

    String url
    HTTPClient httpClient
    RequestBuilder requestBuilder = new RequestBuilder()
    ResponseBuilder responseBuilder = new ResponseBuilder()

    def defaultAcceptHeader
    def defaultContentTypeHeader
    String defaultCharset = 'UTF-8'

    RESTClient(HTTPClient client=new HTTPClient()) {
        this.httpClient = client
    }

    RESTClient(String url, HTTPClient client=new HTTPClient()) {
        this.url = url
        this.httpClient = client
    }

    @NonCPS void setAuthorization(HTTPAuthorization authorization) {
        this.httpClient.authorization = authorization
    }

    @NonCPS Response head(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.HEAD, params, content)
    }

    @NonCPS Response get(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.GET, params, content)
    }

    @NonCPS Response delete(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.DELETE, params, content)
    }

    @NonCPS Response post(Closure content) {
        return post([:], content)
    }

    @NonCPS Response post(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.POST, params, content)
    }

   @NonCPS Response put(Closure content) {
        return put([:], content)
    }

    @NonCPS Response put(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.PUT, params, content)
    }

    @NonCPS Response patch(Closure content) {
        patch([:], content)
    }

    @NonCPS Response options(Map params=[:], Closure content=null) {
        return executeMethod(HTTPMethod.OPTIONS, params, content)
    }


    @NonCPS Response patch(Map params=[:], Closure content=null) {
        Map newParams = new LinkedHashMap(params ?: [:])
        if (newParams.headers) {
            newParams.headers[HTTP.X_HTTP_METHOD_OVERRIDE_HEADER] = HTTPMethod.PATCH.toString()
        } else {
            Map override = [:]
            override[HTTP.X_HTTP_METHOD_OVERRIDE_HEADER] = HTTPMethod.PATCH.toString()
            newParams['headers'] = override
        }
        return executeMethod(HTTPMethod.POST, newParams, content)
    }

    @NonCPS private Response executeMethod(HTTPMethod method, Map params) {
        executeMethod(method, params, null)
    }

    @NonCPS private Response executeMethod(HTTPMethod method, Map params, Closure content) {
        Map requestParams = createRequestParams(params)
        setDefaultAcceptParam(requestParams)
        byte[] data = null
        if (content) {
            def contentBuilder = new ContentBuilder(defaultContentTypeHeader, defaultCharset).build(content)
            setDefaultContentHeader(requestParams, contentBuilder.contentTypeHeader)
            data = contentBuilder.data
        }
        HTTPRequest httpRequest
        HTTPResponse httpResponse
        try {
            httpRequest = requestBuilder.build(method, url, requestParams, data)
            httpResponse = httpClient.execute(httpRequest)
        } catch (HTTPClientException httpEx) {
            throw new RESTClientException(httpEx.message, httpEx, httpEx.request, httpEx.response)
        } catch (Exception ex) {
            throw new RESTClientException(ex.message, ex, httpRequest, httpResponse)
        }
        return buildResponse(httpRequest, httpResponse)
    }

    @NonCPS private createRequestParams(Map params) {
        Map requestParams = new LinkedHashMap(params ?: [:])
        Map headerMap = new TreeMap(String.CASE_INSENSITIVE_ORDER)
        if (params.headers) {
            headerMap.putAll(params.headers)
        }
        requestParams.headers = headerMap
        return requestParams
    }

   @NonCPS  private Response buildResponse(httpRequest, httpResponse) {
        Response response
        try {
            response = responseBuilder.build(httpRequest, httpResponse)
        } catch (Exception ex) {
            throw new RESTContentParseException(ex.message, ex, httpRequest, httpResponse)
        }
        return response
    }

    @NonCPS private void setDefaultAcceptParam(params) {
        if (!params.containsKey('accept') && defaultAcceptHeader) {
            params.headers[HTTP.ACCEPT_HEADER] = (defaultAcceptHeader instanceof ContentType) ?
                             defaultAcceptHeader.acceptHeader : defaultAcceptHeader.toString()
        }
    }

    @NonCPS private void setDefaultContentHeader(params, contentType) {
        if (!params.headers.containsKey(HTTP.CONTENT_TYPE_HEADER)) {
            params.headers[HTTP.CONTENT_TYPE_HEADER] = contentType
        }
    }

}
