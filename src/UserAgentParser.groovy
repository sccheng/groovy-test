import groovy.json.JsonOutput

import java.util.regex.Pattern

class UserAgentParser {
    def static parse(String userAgent) {
        if(userAgent == null || userAgent.isEmpty()) { return }

        String reg = '(?i).*((java|py|node|swift|ruby|go).*?)[-/ ].*?(v?((\\d+)(\\.\\d+)+)).*'
        Pattern p = Pattern.compile(reg)
        def matcher = p.matcher(userAgent)
        def language = ""
        def version = ""
        if(matcher.matches()) {
            language = matcher.group(1)
            version = matcher.group(4)
        }
        return JsonOutput.toJson([sdk_language: language, sdk_version: version, raw: userAgent])
    }
}
