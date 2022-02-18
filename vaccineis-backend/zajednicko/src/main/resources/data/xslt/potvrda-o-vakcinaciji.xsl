<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:potvrda="http://www.ftn.uns.ac.rs/potvrda-o-vakcinaciji"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</title>
                <style type="text/css">
                    span.cls_002{font-family:"DejaVu Sans",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:"DejaVu Sans",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_003{font-family:"DejaVu Sans",serif;font-size:8.2px;color:rgb(128,128,128);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_003{font-family:"DejaVu Sans",serif;font-size:8.2px;color:rgb(128,128,128);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_004{font-family:"DejaVu Sans Bold",serif;font-size:10.6px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_004{font-family:"DejaVu Sans Bold",serif;font-size:10.6px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_005{font-family:"DejaVu Sans Bold",serif;font-size:8.2px;color:rgb(128,128,128);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_005{font-family:"DejaVu Sans Bold",serif;font-size:8.2px;color:rgb(128,128,128);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_006{font-family:"DejaVu Sans Bold",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_006{font-family:"DejaVu Sans Bold",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                </style>
            </head>
            <body>

                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="595" height="841"/>
                    </div>
                    <img src="../../../static/images/Screenshot from 2022-02-08 02-50-25.png" style="position: absolute; left: 55px; top: 30px; height: 85px;"/>
                    <img src="../../../static/images/Screenshot from 2022-02-08 02-49-37.png" style="position: absolute; left: 300px; top: 30px; height: 85px;"/>
                    <div style="position:absolute;left:34px;top:126px" class="cls_002"><span class="cls_002">Шифра потврде вакцинације:
                        <xsl:value-of select="//potvrda:potvrdaVakcinacije/@sifra"/></span>
                    </div>
                    <div style="position:absolute;left:34px;top:140px" class="cls_003"><span class="cls_003">Šifra potvrde / Confirmation code</span></div>
                    <div style="position:absolute;left:120px;top:165px" class="cls_004"><span class="cls_004">ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</span></div>
                    <div style="position:absolute;left:172px;top:181px" class="cls_003"><span class="cls_003">POTVRDA O IZVRŠENOJ VAKCINACIJI </span><span class="cls_005">PROTIV COVID-19</span></div>
                    <div style="position:absolute;left:186px;top:194px" class="cls_003"><span class="cls_003">CONFIRMATION OF THE </span><span class="cls_005">COVID-19</span><span class="cls_003"> VACCINATION</span></div>
                    <div style="position:absolute;left:34px;top:217px" class="cls_006"><span class="cls_006">Име и презиме:
                        <span style="margin-right: 3px;"><xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:ime"/></span> <xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:prezime"/></span>
                    </div>
                    <div style="position:absolute;left:34px;top:231px" class="cls_003"><span class="cls_003">Ime i prezime / First and Last Name</span></div>
                    <div style="position:absolute;left:34px;top:256px" class="cls_006"><span class="cls_006">Датум рођења:
                        <xsl:value-of select="format-date(//potvrda:podaciVakcinisanog/zaj:datumRodjenja, '[D01].[M01].[Y0001]')"/></span>
                    </div>
                    <div style="position:absolute;left:34px;top:268px" class="cls_003"><span class="cls_003">Datum rođenja / Date Of Birth</span></div>
                    <div style="position:absolute;left:34px;top:292px" class="cls_006"><span class="cls_006">Пол:
                        <xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:pol"/></span>
                    </div>
                    <xsl:choose>
                        <xsl:when test="//potvrda:podaciVakcinisanog/zaj:pol = 'Мушко'">
                            <div style="position:absolute;left:34px;top:306px" class="cls_003"><span class="cls_003">Pol: Muško / Gender: Male</span></div>
                        </xsl:when>
                        <xsl:when test="//potvrda:podaciVakcinisanog/zaj:pol = 'Женско'">
                            <div style="position:absolute;left:34px;top:306px" class="cls_003"><span class="cls_003">Pol: Žensko / Gender: Female</span></div>
                        </xsl:when>
                    </xsl:choose>
                    <div style="position:absolute;left:34px;top:329px" class="cls_006"><span class="cls_006">ЈМБГ:
                        <xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:jmbg"/></span>
                    </div>
                    <div style="position:absolute;left:34px;top:343px" class="cls_003"><span class="cls_003">JMBG / Personal No.</span></div>

                    <xsl:for-each select="//potvrda:podaciVakcinacije/potvrda:vakcinaPodaci">
                        <xsl:if test="zaj:brojDoze = '1'">
                            <div style="position:absolute;left:34px;top:366px" class="cls_006"><span class="cls_006">Датум давања и број серије прве дозе вакцине:
                                <xsl:value-of select="format-date(zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/>, серија: <xsl:value-of select="zaj:serijaDoze"/></span></div>
                            <div style="position:absolute;left:34px;top:380px" class="cls_003"><span class="cls_003">Datum vakcinacije / Vaccination Date</span></div>
                        </xsl:if>
                        <xsl:if test="zaj:brojDoze = '2'">
                            <div style="position:absolute;left:34px;top:404px" class="cls_006"><span class="cls_006">Датум давања и број серије друге дозе вакцине:
                                <xsl:value-of select="format-date(zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/>, серија: <xsl:value-of select="zaj:serijaDoze"/></span></div>
                            <div style="position:absolute;left:34px;top:417px" class="cls_003"><span class="cls_003">Datum druge vakcinacije / Second Vaccination Date</span></div>

                            <div style="position:absolute;left:34px;top:441px" class="cls_006"><span class="cls_006">Здравствена установа која вакцинише:
                                <xsl:value-of select="zaj:zdravstvenaUstanova"/></span>
                            </div>
                            <div style="position:absolute;left:34px;top:455px" class="cls_003"><span class="cls_003">Zdravstvena ustanova koja vakciniše / Health care institution of vaccination</span></div>

                            <div style="position:absolute;left:34px;top:480px" class="cls_006"><span class="cls_006">Назив вакцине:
                                <xsl:value-of select="zaj:nazivVakcine"/></span>
                            </div>
                            <div style="position:absolute;left:34px;top:494px" class="cls_003"><span class="cls_003">Naziv vakcine / Name of vaccine</span></div>
                        </xsl:if>
                    </xsl:for-each>

                    <div style="position:absolute;left:34px;top:518px" class="cls_006"><span class="cls_006">Датум издавања потврде:
                        <xsl:value-of select="format-date(//potvrda:datumIzdavanjaPotvrde, '[D01].[M01].[Y0001]')"/></span>
                    </div>
                    <div style="position:absolute;left:34px;top:532px" class="cls_003"><span class="cls_003">Datum izdavanja potvrde / Confirmation Release Date</span></div>
                    <div style="position:absolute;right:34px;top:578px" class="cls_006"><span class="cls_006">Здравствена установа: Институт за јавно здравље Србије Др Милан Јовановић Батут</span></div>
                    <div style="position:absolute;left:385px;top:591px" class="cls_003"><span class="cls_003">Zdravstvena ustanova / Medical institution</span></div>
                    <div style="position:absolute;left:34px;top:690px" class="cls_002"><span class="cls_002">Ова потврда важи без потписа и печата</span></div>
                    <div style="position:absolute;left:34px;top:703px" class="cls_003"><span class="cls_003">Ova potvrda važi bez potpisa i pečata / This certificate is valid without signatures and seals</span></div>
                    <img style="position: absolute; right: 34px; top: 610px; height: 105px;">
                        <xsl:attribute name="src">
                            <xsl:value-of select="//potvrda:QRKod"/>
                        </xsl:attribute>
                    </img>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>