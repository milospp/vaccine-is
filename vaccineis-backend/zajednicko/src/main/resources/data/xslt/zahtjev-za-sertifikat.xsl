<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zah="http://www.ftn.uns.ac.rs/zahtjevSertifikata"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Захтјев за сертификат</title>
                <style type="text/css">
                    span.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_004{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_004{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_005{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_005{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                </style>
            </head>
            <body>
                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="595" height="841"/>
                    </div>
                    <div style="position:absolute;left:262.20px;top:103.97px" class="cls_002"><span class="cls_002"><b>З А Х Т Е В</b></span></div>
                    <div style="position:absolute;left:157.56px;top:119.69px" class="cls_002"><span class="cls_002"><b>за издавање дигиталног зеленог сертификата</b></span></div>
                    <div style="position:absolute;left:122.64px;top:164.76px" class="cls_003"><span class="cls_003">У складу са одредбом Републике Србије о издавању дигиталног зеленог</span></div>
                    <div style="position:absolute;left:87.60px;top:178.20px" class="cls_003"><span class="cls_003">сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима</span></div>
                    <div style="position:absolute;left:87.60px;top:191.64px" class="cls_003"><span class="cls_003">тестирања на заразну болест SARS-CoV-2 или опоравку од болести COVID-19,</span></div>
                    <div style="position:absolute;left:87.60px;top:205.08px" class="cls_003"><span class="cls_003">подносим захтев за издавање дигиталног зеленог сертификата.</span></div>
                    <div style="position:absolute;left:87.60px;top:245.52px" class="cls_003"><span class="cls_003">Подносилац захтева:</span></div>
                    <div style="position:absolute;left:87.60px;top:272.40px" class="cls_003"><span class="cls_003">Име и презиме:
                        <span class="cls_003" style="margin-right:2px"><b><xsl:value-of select="//zah:podnosilac/zaj:ime"/></b></span><span class="cls_003"><b><xsl:value-of select="//zah:podnosilac/zaj:prezime"/></b></span></span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:285.84px" class="cls_003"><span class="cls_003">Датум рођења:
                        <span class="cls_003"><b><xsl:value-of select="format-date(//zah:podnosilac/zaj:datumRodjenja, '[D01].[M01].[Y0001]')"/></b></span> </span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:299.28px" class="cls_003"><span class="cls_003">Пол:
                        <span class="cls_003"><b><xsl:value-of select="//zah:podnosilac/zaj:pol"/></b></span> </span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:312.72px" class="cls_003"><span class="cls_003">Јединствени матични број грађанина:
                        <span class="cls_003"><b><xsl:value-of select="//zah:podnosilac/zaj:jmbg"/></b></span> </span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:326.16px" class="cls_003"><span class="cls_003">Број пасоша:
                        <span class="cls_003"><b><xsl:value-of select="//zah:podnosilac/zaj:brojPasosa"/></b></span> </span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:353.04px" class="cls_003"><span class="cls_003">Разлог за подношење захтева:
                        <span class="cls_003"><b><xsl:value-of select="//zah:razlog" disable-output-escaping="yes"/></b></span></span>
                    </div>
                    <div style="position:absolute;left:131.88px;top:433.84px" class="cls_004"><span class="cls_004">(навести што прецизнији разлога за подношење захтева за издавање дигиталног пасоша)</span></div>
                    <div style="position:absolute;left:87.60px;top:497.40px" class="cls_003"><span class="cls_003">У
                        <span class="cls_003"><b><xsl:value-of select="//zah:lokacija"/></b></span> </span>
                    </div>
                    <div style="position:absolute;left:87.60px;top:533.16px" class="cls_003"><span class="cls_003">дана
                        <span class="cls_003"><b><xsl:value-of select="format-date(//zah:datum, '[D01].[M01].[Y0001]')"/></b></span> године</span>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>