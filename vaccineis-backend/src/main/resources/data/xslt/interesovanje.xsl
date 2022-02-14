<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:inter="http://www.ftn.uns.ac.rs/interesovanje"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Исказивање интересовања за вакцинисање против COVID-19</title>
                <style type="text/css">
                    span.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_005{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_005{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_004{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_004{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                </style>
            </head>
            <body>
                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="595" height="841"/>
                    </div>
                    <div style="position:absolute;left:106.44px;top:68.93px" class="cls_002"><span class="cls_002"><b>Исказивање интересовања за вакцинисање против COVID-19</b></span></div>
                    <div style="position:absolute;left:87.60px;top:100.32px" class="cls_003"><span class="cls_003">Одаберите опцију:</span></div>
                    <xsl:choose>
                        <xsl:when test="//inter:drzavljanstvo = 'Држављанин Републике Србије'">
                            <div style="position:absolute;left:134.32px;top:113.76px" class="cls_003"><span class="cls_003"><b>Држављанин Републике Србије</b></span></div>
                            <div style="position:absolute;left:134.32px;top:127.20px" class="cls_003"><span class="cls_003">Страни држављанин са боравком у РС</span></div>
                            <div style="position:absolute;left:134.32px;top:140.64px" class="cls_003"><span class="cls_003">Страни држављанин без боравка у РС</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:drzavljanstvo = 'Страни држављанин са боравком у РС'">
                            <div style="position:absolute;left:134.32px;top:113.76px" class="cls_003"><span class="cls_003">Држављанин Републике Србије</span></div>
                            <div style="position:absolute;left:134.32px;top:127.20px" class="cls_003"><span class="cls_003"><b>Страни држављанин са боравком у РС</b></span></div>
                            <div style="position:absolute;left:134.32px;top:140.64px" class="cls_003"><span class="cls_003">Страни држављанин без боравка у РС</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:drzavljanstvo = 'Страни држављанин без боравка у РС'">
                            <div style="position:absolute;left:134.32px;top:113.76px" class="cls_003"><span class="cls_003">Држављанин Републике Србије</span></div>
                            <div style="position:absolute;left:134.32px;top:127.20px" class="cls_003"><span class="cls_003">Страни држављанин са боравком у РС</span></div>
                            <div style="position:absolute;left:134.32px;top:140.64px" class="cls_003"><span class="cls_003"><b>Страни држављанин без боравка у РС</b></span></div>
                        </xsl:when>
                    </xsl:choose>
                    <div style="position:absolute;left:87.60px;top:167.52px" class="cls_003"><span class="cls_003">ЈМБГ:</span></div>
                    <div style="position:absolute;left:134px;top:185px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:jmbg"/></b></span></div>

                    <div style="position:absolute;left:87.60px;top:207.84px" class="cls_003"><span class="cls_003">Име:</span></div>
                    <div style="position:absolute;left:134px;top:225px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:ime"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:248.16px" class="cls_003"><span class="cls_003">Презиме:</span></div>
                    <div style="position:absolute;left:134px;top:265px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:prezime"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:288.36px" class="cls_003"><span class="cls_003">Адреса електронске поште:</span></div>
                    <div style="position:absolute;left:134px;top:305px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:kontakt/zaj:email"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:328.68px" class="cls_003"><span class="cls_003">Број мобилног телефона (навести број у формату 06X..... без размака и цртица):</span></div>
                    <div style="position:absolute;left:134px;top:345px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:kontakt/zaj:mobilniTelefon"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:369.00px" class="cls_003"><span class="cls_003">Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица):</span></div>
                    <div style="position:absolute;left:134px;top:385px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:kontakt/zaj:fiksniTelefon"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:409.32px" class="cls_003"><span class="cls_003">Одаберите локацију где желите да примите вакцину (унесите општину):</span></div>
                    <div style="position:absolute;left:134px;top:425px" class="cls_003"><span class="cls_003"><b> <xsl:value-of select="//inter:lokacijaPrimanja"/> </b></span></div>

                    <div style="position:absolute;left:87.60px;top:449.64px" class="cls_003"><span class="cls_003">Исказујем интересовање да примим искључиво вакцину следећих произвођача за</span></div>
                    <div style="position:absolute;left:87.60px;top:463.08px" class="cls_003"><span class="cls_003">који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и</span></div>
                    <div style="position:absolute;left:87.60px;top:476.52px" class="cls_003"><span class="cls_003">квалитет и изда дозволу за употребу лека:</span></div>

                    <xsl:choose>
                        <xsl:when test="//inter:nazivVakcine = 'Pfizer-BioNTech'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003"><b>Pfizer-BioNTech</b></span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003">Sputnik V (Gamaleya истраживачки центар)</span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003">Sinopharm</span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003">AstraZeneca</span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003">Moderna</span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003">Било која</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:nazivVakcine = 'Sputnik V (Gamaleya истраживачки центар)'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003">Pfizer-BioNTech</span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003"><b>Sputnik V (Gamaleya истраживачки центар)</b></span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003">Sinopharm</span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003">AstraZeneca</span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003">Moderna</span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003">Било која</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:nazivVakcine = 'Sinopharm'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003">Pfizer-BioNTech</span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003">Sputnik V (Gamaleya истраживачки центар)</span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003"><b>Sinopharm</b></span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003">AstraZeneca</span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003">Moderna</span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003">Било која</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:nazivVakcine = 'AstraZeneca'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003">Pfizer-BioNTech</span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003">Sputnik V (Gamaleya истраживачки центар)</span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003">Sinopharm</span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003"><b>AstraZeneca</b></span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003">Moderna</span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003">Било која</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:nazivVakcine = 'Moderna'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003">Pfizer-BioNTech</span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003">Sputnik V (Gamaleya истраживачки центар)</span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003">Sinopharm</span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003">AstraZeneca</span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003"><b>Moderna</b></span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003">Било која</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:nazivVakcine = 'Било која'">
                            <div style="position:absolute;left:134.32px;top:489.84px" class="cls_003"><span class="cls_003">Pfizer-BioNTech</span></div>
                            <div style="position:absolute;left:134.32px;top:503.28px" class="cls_003"><span class="cls_003">Sputnik V (Gamaleya истраживачки центар)</span></div>
                            <div style="position:absolute;left:134.32px;top:516.72px" class="cls_003"><span class="cls_003">Sinopharm</span></div>
                            <div style="position:absolute;left:134.32px;top:530.16px" class="cls_003"><span class="cls_003">AstraZeneca</span></div>
                            <div style="position:absolute;left:134.32px;top:543.60px" class="cls_003"><span class="cls_003">Moderna</span></div>
                            <div style="position:absolute;left:134.32px;top:557.04px" class="cls_003"><span class="cls_003"><b>Било која</b></span></div>
                        </xsl:when>
                    </xsl:choose>
                    <div style="position:absolute;left:87.60px;top:583.92px" class="cls_003"><span class="cls_003">Да ли сте добровољни давалац крви?</span></div>
                    <xsl:choose>
                        <xsl:when test="//inter:davalacKrvi = 'Да'">
                            <div style="position:absolute;left:134.32px;top:597.36px" class="cls_003"><span class="cls_003"><b>Да</b></span></div>
                            <div style="position:absolute;left:134.32px;top:610.80px" class="cls_003"><span class="cls_003">Не</span></div>
                        </xsl:when>
                        <xsl:when test="//inter:davalacKrvi = 'Не'">
                            <div style="position:absolute;left:134.32px;top:597.36px" class="cls_003"><span class="cls_003">Да</span></div>
                            <div style="position:absolute;left:134.32px;top:610.80px" class="cls_003"><span class="cls_003"><b>Не</b></span></div>
                        </xsl:when>
                    </xsl:choose>
                    <div style="position:absolute;left:87.60px;top:726.12px" class="cls_003"><span class="cls_003">дана
                        <b><xsl:value-of select="format-date(//inter:datum, '[D01].[M01].[Y0001]')"/></b> године</span>
                    </div>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>