import groovy.json.JsonOutput

import java.util.regex.Pattern

class UserAgentParser {
    def static parse(String userAgent) {
        if(userAgent == null || userAgent.isEmpty()) { return }

        // Referred to the following docs when building regex:
        // http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#CASE_INSENSITIVE
        String reg = '(?i).*?((java|py|node|swift|ruby|go).*?)[-/ ].*?(v?(\\d+)(\\.\\d+)+).*?([(]{1}?.*?[)]{1}?)??'
        Pattern p = Pattern.compile(reg)
        def matcher = p.matcher(userAgent)
        
        def language = ""
        def version = ""
        def operatingSystem = ""
        if(matcher.matches()) {
            language = matcher.group(1)
            version = matcher.group(3)
            if(matcher.group(6) != null) {
                operatingSystem = matcher.group(6)
            }
        }
        return JsonOutput.toJson([sdk_language: language, sdk_version: version, os: operatingSystem, raw: userAgent])
    }
}
