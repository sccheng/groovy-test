import groovy.json.JsonOutput

class UserAgentParserTests extends GroovyTestCase {

    void testEmptyNullAgent() {
        assertEquals(null, UserAgentParser.parse(null))
    }

    void testEmptyUserAgent() {
        assertEquals(null, UserAgentParser.parse(""))
    }

    void testPythonUserAgent1() {
        String userAgent = "python-requests/2.18.1"
        def expected = JsonOutput.toJson([sdk_language: "python", sdk_version: "2.18.1", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testPythonUserAgent2() {
        String userAgent = "watson-developer-cloud-python-0.26.0"
        def expected = JsonOutput.toJson([sdk_language: "python", sdk_version: "0.26.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should the language be Python? or Python-urllib?
    void testPythonUserAgent3() {
        String userAgent = "Python-urllib/2.7"
        def expected = JsonOutput.toJson([sdk_language: "Python-urllib", sdk_version: "2.7", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testPythonUserAgent4() {
        String userAgent = "PycURL/7.43.0 libcurl/7.35.0 OpenSSL/1.0.1f zlib/1.2.8 libidn/1.28 librtmp/2.3"
        def expected = JsonOutput.toJson([sdk_language: "PycURL", sdk_version: "7.43.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testJavaUserAgent1() {
        String userAgent = "watson-apis-java-sdk/3.7.2 (java.vendor=Oracle Corporation; java.version=1.8.0_45; os.arch=amd64; os.name=Linux; os.version=4.1.13-19.30.amzn1.x86_64)"
        def expected = JsonOutput.toJson([sdk_language: "java", sdk_version: "3.7.2", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // What should the expected results be? Right now, it returns empty strings for language and version.
    void testJavaUserAgent2() {
        String userAgent = "watson-apis-java-sdk/unknown-version"
        def expected = JsonOutput.toJson([sdk_language: "", sdk_version: "", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should the version here be 3.5.10-SNAPSHOT or 3.5.10?
    void testJavaUserAgent3() {
        String userAgent = "watson-apis-java-sdk/3.5.10-SNAPSHOT"
        def expected = JsonOutput.toJson([sdk_language: "java", sdk_version: "3.5.10-SNAPSHOT", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testJavaUserAgent4() {
        String userAgent = "watson-developer-cloud-java-wrapper-2.0.0"
        def expected = JsonOutput.toJson([sdk_language: "java", sdk_version: "2.0.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should the version here be 1.8.0 or 1.8.0_91?
    void testJavaUserAgent5() {
        String userAgent = "Java/1.8.0_91"
        def expected = JsonOutput.toJson([sdk_language: "Java", sdk_version: "1.8.0_91", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should language be Java or Apache-HttpClient?
    void testJavaUserAgent6() {
        String userAgent = "Apache-HttpClient/4.4.1 (Java/1.8.0)"
        def expected = JsonOutput.toJson([sdk_language: "Java", sdk_version: "1.8.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should language be Java or Apache-CXF? Or should this just be empty (maybe we don't track this one)?
    void testJavaUserAgent7() {
        String userAgent = "Apache-CXF/3.1.11"
        def expected = JsonOutput.toJson([sdk_language: "", sdk_version: "", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Do we want to track this?
    void testJavaUserAgent8() {
        String userAgent = "okhttp/2.7.2"
        def expected = JsonOutput.toJson([sdk_language: "", sdk_version: "", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Should we list the language as node or nodejs?
    void testNodeUserAgent1() {
        String userAgent = "watson-developer-cloud-nodejs-2.33.0"
        def expected = JsonOutput.toJson([sdk_language: "nodejs", sdk_version: "2.33.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    // Do we want to track this?
    void testNodeUserAgent2() {
        String userAgent = "node-XMLHttpRequest"
        def expected = JsonOutput.toJson([sdk_language: "", sdk_version: "", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testSwiftUserAgent1() {
        String userAgent = "watson-apis-swift-sdk/0.14.0 iOS/10.3.2"
        def expected = JsonOutput.toJson([sdk_language: "swift", sdk_version: "0.14.0", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testGoUserAgent1() {
        String userAgent = "Go-http-client/1.1"
        def expected = JsonOutput.toJson([sdk_language: "Go", sdk_version: "1.1", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

    void testGoUserAgent2() {
        String userAgent = "Golang Watson SDK v0.1"
        def expected = JsonOutput.toJson([sdk_language: "Golang", sdk_version: "v0.1", raw: userAgent])
        assertEquals(expected, UserAgentParser.parse(userAgent))
    }

//    void testPrintFileContents() {
//        def file1 = new File('user-agent-examples.txt')
//        def file1Lines = file1.readLines()
//        for (String line: file1Lines) {
//            println UserAgentParser.parse(line)
//        }
//    }
}
