<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:izvjestaj="http://www.ftn.uns.ac.rs/izvjestaj"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Извештај о имунизацији</title>
                <style type="text/css">
                    span.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:Times,serif;font-size:13.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_003{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_004{font-family:Arial,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_004{font-family:Arial,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_007{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: underline}
                    div.cls_007{font-family:Times,serif;font-size:11.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_006{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_006{font-family:Times,serif;font-size:8.8px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_005{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_005{font-family:Times,serif;font-size:9.7px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    table { border-collapse: collapse; }
                </style>
            </head>
            <body>

                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="595" height="841"/>
                    </div>
                    <div style="position:absolute;left:221.40px;top:68.93px" class="cls_002"><span class="cls_002">Извештај о имунизацији</span></div>
                    <div style="position:absolute;left:87.60px;top:127.20px" class="cls_003"><span class="cls_003">Извештај се односи на период од
                        <b> <xsl:value-of select="format-date(//izvjestaj:period/izvjestaj:pocetakPerioda, '[D01].[M01].[Y0001]')"/> </b> до <b> <xsl:value-of select="format-date(//izvjestaj:period/izvjestaj:krajPerioda, '[D01].[M01].[Y0001]')"/> </b></span></div>
                    <div style="position:absolute;left:87.60px;top:167.52px" class="cls_003"><span class="cls_003">У напоменутом временском интервалу је:</span></div>
                    <div style="position:absolute;left:113.52px;top:181.80px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   поднето
                        <b> <xsl:value-of select="//izvjestaj:brojInteresovanjaImunizacija"/> </b> докумената  о интересовању за имунизацију;</span></div>
                    <div style="position:absolute;left:113.52px;top:196.08px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   примљено
                        <b> <xsl:value-of select="//izvjestaj:brojZahtjeva"/> </b> захтева за дигитални зелени сертификат, од којих је
                        <b> <xsl:value-of select="//izvjestaj:izdatoZahtjeva"/> </b></span></div>
                    <div style="position:absolute;left:131.04px;top:209.52px" class="cls_003"><span class="cls_003">издато.</span></div>
                    <div style="position:absolute;left:87.60px;top:263.28px" class="cls_003"><span class="cls_003">Дато је
                        <b> <xsl:value-of select="//izvjestaj:brojDoza"/> </b> доза вакцине против COVID-19 вируса у следећој количини:</span></div>
                    <table style="position:absolute;left:82px;top:289px; border: black 1px solid; border-collapse: collapse; width: 408px; min-height: 70px;" class="cls_003">
                        <tr style="border: 1px solid black; width: inherit; height: 16px;">
                            <th style="border-right: 1px solid black;"><div class="cls_003" style="position:absolute;left:70px;top:2px;"><span class="cls_003"><b>Редни број дозе</b></span></div></th>
                            <th><div style="position:absolute;left:270px;top:2px" class="cls_003"><span class="cls_003"><b>Број датих доза</b></span></div></th>
                        </tr>
                        <xsl:for-each select="//izvjestaj:dozeVakcinaInfo/izvjestaj:dozaInfo">
                            <xsl:if test="izvjestaj:redniBrojDoze = '1'">
                                <tr style="border: 1px solid black; height: 16px;">
                                    <td style="border-right: 1px solid black;"><div style="position:absolute;left:107px;top:20px" class="cls_003"><span class="cls_003"><b>1</b></span></div></td>
                                    <td><div style="position:absolute;left:296px;top:20px" class="cls_003">
                                        <span class="cls_003"><xsl:value-of select="izvjestaj:brojDatihDoza"/></span></div></td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="izvjestaj:redniBrojDoze = '2'">
                                <tr style="border: 1px solid black; height: 16px;">
                                    <td style="border-right: 1px solid black;"><div style="position:absolute;left:107px;top:38px" class="cls_003"><span class="cls_003"><b>2</b></span></div></td>
                                    <td><div style="position:absolute;left:296px;top:38px" class="cls_003">
                                        <span class="cls_003"><xsl:value-of select="izvjestaj:brojDatihDoza"/></span></div></td>
                                </tr>
                            </xsl:if>
                            <xsl:if test="izvjestaj:redniBrojDoze = '3'">
                                <tr style="border: 1px solid black; height: 16px;">
                                    <td style="border-right: 1px solid black;"><div style="position:absolute;left:107px;top:56px" class="cls_003"><span class="cls_003"><b>3</b></span></div></td>
                                    <td><div style="position:absolute;left:296px;top:56px" class="cls_003">
                                        <span class="cls_003"><xsl:value-of select="izvjestaj:brojDatihDoza"/></span></div></td>
                                </tr>
                            </xsl:if>
                        </xsl:for-each>
                    </table>

                    <div style="position:absolute;left:87.60px;top:399.96px" class="cls_003"><span class="cls_003">Расподела по произвођачима је:</span></div>
                    <xsl:for-each select="//izvjestaj:raspodjelaDoza/izvjestaj:tipVakcineInfo">
                        <xsl:if test="izvjestaj:nazivProizvodjaca = 'Pfizer, BioNTech'">
                            <div style="position:absolute;left:105.12px;top:414.24px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   <b>Pfizer, BioNTech -
                                <xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></b> доза;</span>
                            </div>
                        </xsl:if>
                        <xsl:if test="izvjestaj:nazivProizvodjaca = 'Sinopharm'">
                            <div style="position:absolute;left:105.12px;top:428.40px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   <b>Sinopharm -
                                <xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></b> доза;</span>
                            </div>
                        </xsl:if>
                        <xsl:if test="izvjestaj:nazivProizvodjaca = 'Sputnik V'">
                            <div style="position:absolute;left:105.12px;top:442.80px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   <b>Sputnik V -
                                <xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></b> доза;</span>
                            </div>
                        </xsl:if>
                        <xsl:if test="izvjestaj:nazivProizvodjaca = 'AstraZeneca, Oxford'">
                            <div style="position:absolute;left:105.12px;top:457.08px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   <b>AstraZeneca, Oxford -
                                <xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></b> доза;</span>
                            </div>
                        </xsl:if>
                        <xsl:if test="izvjestaj:nazivProizvodjaca = 'Moderna'">
                            <div style="position:absolute;left:105.12px;top:472px" class="cls_004"><span class="cls_004">&#9679;</span><span class="cls_003">   <b>Moderna -
                                <xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></b> доза;</span>
                            </div>
                        </xsl:if>
                    </xsl:for-each>

                    <div style="position:absolute;left:87.60px;top:557.76px" class="cls_003"><span class="cls_003">Датум издавања: </span><span class="cls_007">
                        <xsl:value-of select="format-date(//izvjestaj:datumIzdavanjaIzvjestaja, '[D01].[M01].[Y0001]')"/>
                    </span><span class="cls_003"> године</span></div>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>