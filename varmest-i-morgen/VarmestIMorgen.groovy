import groovy.json.JsonSlurper

System.setProperty("file.encoding", "UTF-8")
    
FORSLAG_URL = "http://www.yr.no/_/websvc/jsonforslagsboks.aspx?s=" 
// Gir: [["Bergen",,,,],[["Bergen","/sted/Norge/Hordaland/Bergen/Bergen/","By (Hordaland)","NO"],[..flere 'Bergen' i verden..]
    
String hentVarselUrl(by) {
    def json = new URL(FORSLAG_URL + URLEncoder.encode(by)).text
    def forslag = new JsonSlurper().parseText(json.replace(',,,,', ',')) // Bug i JsonParser ...
    def førsteByINorge = forslag[1].find { 
        it[2].startsWith("By") && it[3] == 'NO'
    }
    'http://www.yr.no' + førsteByINorge[1] + 'varsel.xml'
}

int finnMaxTempForBy(by, url, dato) {
    def vær = new XmlSlurper().parse(url)
    def væretIMorgen = vær.forecast.tabular.time.findAll {
        it.@from.text().substring(0,10) == dato.format('yyyy-MM-dd')
    }
    væretIMorgen*.temperature*.@value*.toInteger().max()
}

Map finnMaxTempPerBy(byer, dato) {
    byer.collectEntries { by ->
        [(by): finnMaxTempForBy(by, hentVarselUrl(by), dato)]
    }
}

def byer = ["Oslo", "Bergen", "Trondheim", "Tromsø", "Kristiansand", "Drammen", "Stavanger", "Haugesund", "Fredrikstad"]
def imorgen = new Date()+1

def maxTempPerBy = finnMaxTempPerBy(byer, imorgen)
maxTempPerBy.findAll { 
    it.value == maxTempPerBy.values().max()
}
