class Tests extends GroovyTestCase {

    void testEqual() {
        assert 1 == 1
    }

    void testEmptyNullAgent() {
        println UserAgentParser.parse(null)
    }

    void testEmptyUserAgent() {
        println UserAgentParser.parse("")
    }

    void testValidUserAgent() {
        println UserAgentParser.parse("watson-apis-java-sdk/3.7.2")
    }

    void testOldUserAgent() {
        println UserAgentParser.parse("watson-developer-cloud-python-0.26.0")
    }

    void testGenericJavaUserAgent() {
        println UserAgentParser.parse("Java/1.7.0_45")
    }

    void testPrintFileContents() {
        def file1 = new File('user-agent-examples.txt')
        def file1Lines = file1.readLines()
        for (String line: file1Lines) {
            println UserAgentParser.parse(line)
        }
    }
}
