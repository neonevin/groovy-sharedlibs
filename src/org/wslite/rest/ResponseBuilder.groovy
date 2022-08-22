/* Copyright 2012-2014 the original author or authors.
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

import groovy.json.JsonSlurper
import org.wslite.http.ContentTypeHeader
import org.wslite.http.HTTPRequest
import org.wslite.http.HTTPResponse

import javax.xml.parsers.SAXParserFactory
import groovy.xml.FactorySupport;

class ResponseBuilder {

    @NonCPS Response build(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        Response response = new Response(httpRequest, httpResponse)
        if (isTextResponse(httpResponse)) {
            String responseText = httpResponse.getContentAsString()
            response.text = responseText
            if (responseText && isXmlResponse(httpResponse)) {
                response.xml = parseXmlContent(responseText)
            }
            if (responseText && isJsonResponse(httpResponse)) {
                response.json = parseJsonContent(responseText)
            }
        }
        return response
    }

    @NonCPS private boolean isTextResponse(HTTPResponse httpResponse) {
        def contentType = contentTypeNoParameter(httpResponse)
        return contentType?.startsWith('text/') ||
                ContentType.TEXT.contains(contentType) ||
                ContentType.HTML.contains(contentType) ||
                ContentType.XML.contains(contentType) ||
                ContentType.JSON.contains(contentType)
    }

   @NonCPS  private boolean isXmlResponse(HTTPResponse httpResponse) {
        return ContentType.XML.contains(contentTypeNoParameter(httpResponse))
    }

   @NonCPS  private boolean isJsonResponse(HTTPResponse httpResponse) {
        return ContentType.JSON.contains(contentTypeNoParameter(httpResponse))
    }

    @NonCPS private parseXmlContent(String content) {
        SAXParserFactory factory = FactorySupport.createSaxParserFactory();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        return new XmlSlurper(factory.newSAXParser()).parseText(content)
    }

   @NonCPS  private parseJsonContent(String content) {
        return new JsonSlurper().parseText(content)
    }

    @NonCPS private String contentTypeNoParameter(HTTPResponse httpResponse) {
        return new ContentTypeHeader(httpResponse.contentType).mediaType
    }
}
